package com.anandabs.anandabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;

public class signingAct extends AppCompatActivity {
    private EditText et_emails, et_passwords, et_lastname, et_mobilenum,et_repassword,et_firstname;
    private ImageButton btn_sigbtn;
    private LottieAnimationView anim2023al, anim2024al, anim2025al, anim2026al;
    private TextView btn2023, btn2024, btn2025, btn2026;


    String id_num;
    String password;
    String name_user_last;
    String name_user_first;
    String mob_num;
    String year;
    String reenter_password;


    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference2;
    private DatabaseReference databaseReference3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_signing);


        year = "NO";

        et_emails = findViewById(R.id.et_emails);
        et_passwords = findViewById(R.id.et_passwords);
        btn_sigbtn = findViewById(R.id.btn_sigbtn);
        et_lastname = findViewById(R.id.et_lastname);
        et_repassword = findViewById(R.id.et_repassword);
        et_firstname = findViewById(R.id.et_firstname);
        et_mobilenum = findViewById(R.id.et_mobilenum);
        btn2023 = findViewById(R.id.btn2023);
        btn2024 = findViewById(R.id.btn2024);
        btn2025 = findViewById(R.id.btn2025);
        btn2026 = findViewById(R.id.btn2026);


        anim2023al = findViewById(R.id.anim2023al);
        anim2024al = findViewById(R.id.anim2024al);
        anim2025al = findViewById(R.id.anim2025al);
        anim2026al = findViewById(R.id.anim2026al);

        anim2026al.setVisibility(View.GONE);
        anim2026al.pauseAnimation();
        anim2024al.setVisibility(View.GONE);
        anim2024al.pauseAnimation();
        anim2025al.setVisibility(View.GONE);
        anim2025al.pauseAnimation();
        anim2023al.setVisibility(View.GONE);
        anim2023al.pauseAnimation();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        btn2023.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = String.valueOf(2023);

                anim2023al.setVisibility(View.VISIBLE);
                anim2023al.playAnimation();
                anim2024al.setVisibility(View.GONE);
                anim2024al.pauseAnimation();
                anim2025al.setVisibility(View.GONE);
                anim2025al.pauseAnimation();
                anim2026al.setVisibility(View.GONE);
                anim2026al.pauseAnimation();
            }
        });

        btn2024.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = String.valueOf(2024);
                anim2024al.setVisibility(View.VISIBLE);
                anim2024al.playAnimation();
                anim2023al.setVisibility(View.GONE);
                anim2023al.pauseAnimation();
                anim2025al.setVisibility(View.GONE);
                anim2025al.pauseAnimation();
                anim2026al.setVisibility(View.GONE);
                anim2026al.pauseAnimation();
            }
        });

        btn2025.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = String.valueOf(2025);
                anim2025al.setVisibility(View.VISIBLE);
                anim2025al.playAnimation();
                anim2024al.setVisibility(View.GONE);
                anim2024al.pauseAnimation();
                anim2023al.setVisibility(View.GONE);
                anim2023al.pauseAnimation();
                anim2026al.setVisibility(View.GONE);
                anim2026al.pauseAnimation();
            }
        });

        btn2026.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = String.valueOf(2026);
                anim2026al.setVisibility(View.VISIBLE);
                anim2026al.playAnimation();
                anim2024al.setVisibility(View.GONE);
                anim2024al.pauseAnimation();
                anim2025al.setVisibility(View.GONE);
                anim2025al.pauseAnimation();
                anim2023al.setVisibility(View.GONE);
                anim2023al.pauseAnimation();
            }
        });

        databaseReference2 = firebaseDatabase.getReference(String.valueOf(year));


        btn_sigbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_num = et_emails.getText().toString().trim();
                password = et_passwords.getText().toString().trim();
                name_user_first = et_firstname.getText().toString().trim();
                name_user_last = et_lastname.getText().toString().trim();
                mob_num = et_mobilenum.getText().toString().trim();
                reenter_password = et_repassword.getText().toString().trim();

                if (id_num.isEmpty()) {
                    Toasty.warning(getApplicationContext(), "Enter Your Id Nomber", Toasty.LENGTH_LONG, true).show();
                } else {
                    if (password.isEmpty()) {
                        Toasty.warning(getApplicationContext(), "Enter Your Password", Toasty.LENGTH_LONG, true).show();
                    } else {
                        if (reenter_password.isEmpty()) {
                            Toasty.warning(getApplicationContext(), "Re-Enter Your Password", Toasty.LENGTH_LONG, true).show();
                        } else {
                            if (reenter_password.equals(password) ) {

                                if (name_user_first.isEmpty()) {
                                    Toasty.warning(getApplicationContext(), "Enter Your Name", Toasty.LENGTH_LONG, true).show();
                                } else {
                                    if (name_user_last.isEmpty()) {
                                        Toasty.warning(getApplicationContext(), "Enter Your Name", Toasty.LENGTH_LONG, true).show();
                                    } else {
                                        if (mob_num.isEmpty()) {
                                            Toasty.warning(getApplicationContext(), "Enter Your Mobile Nomber", Toasty.LENGTH_LONG, true).show();
                                        } else {
                                            if (year == "NO") {
                                                Toasty.warning(getApplicationContext(), "Tap Your Exam Year", Toasty.LENGTH_LONG, true).show();
                                            } else {
                                                databaseReference3 = firebaseDatabase.getReference("verifyacc");
                                                SignModule signModule = new SignModule(id_num, name_user_first + " " + name_user_last, mob_num, year, password, name_user_first);
                                                databaseReference3.child(id_num).setValue(signModule).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toasty.success(getApplicationContext(), "Registed.", Toast.LENGTH_SHORT, true).show();
                                                        et_emails.setText("");
                                                        et_passwords.setText("");
                                                        et_firstname.setText("");
                                                        et_lastname.setText("");
                                                        et_mobilenum.setText("");
                                                        et_repassword.setText("");
                                                    }

                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toasty.error(getApplicationContext(), "Register Failed", Toasty.LENGTH_LONG, true).show();
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                            } else {
                                Toasty.warning(getApplicationContext(),"Password is not Match", Toasty.LENGTH_LONG, true).show();
                            }
                        }
                    }



                }


        };

    });
}}