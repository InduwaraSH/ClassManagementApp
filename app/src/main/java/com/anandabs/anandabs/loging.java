package com.anandabs.anandabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import es.dmoral.toasty.Toasty;

public class loging extends AppCompatActivity {
private ImageButton btn_sgnl,btn_logging;
private EditText et_pwdlog,et_emaill;
private LottieAnimationView plz_wait;
private TextView textView6;



String e_mail;
String password;

private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loging);

        btn_sgnl=findViewById(R.id.btn_sgnl);
        btn_logging=findViewById(R.id.btn_logging);
        et_emaill=findViewById(R.id.et_emaill);
        et_pwdlog=findViewById(R.id.et_pwdlog);
        plz_wait=findViewById(R.id.plz_wait);
        textView6=findViewById(R.id.textView6);

        plz_wait.pauseAnimation();
        plz_wait.setVisibility(View.GONE);

        firebaseAuth=FirebaseAuth.getInstance();


        btn_sgnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),signingAct.class);
                startActivity(intent);
            }
        });

        btn_logging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e_mail=et_emaill.getText().toString().trim();
                password=et_pwdlog.getText().toString().trim();

                if (e_mail.isEmpty()){
                    Toasty.warning(getApplicationContext(),"Enter Email",Toasty.LENGTH_LONG).show();
                    if (password.isEmpty()){
                        Toasty.warning(getApplicationContext(),"Enter Email and Password",Toasty.LENGTH_LONG).show();

                    }

                }
                else {
                    if (password.isEmpty()){
                        Toasty.warning(getApplicationContext(),"Enter Password",Toasty.LENGTH_LONG).show();

                    }
                    else {
                        textView6.setVisibility(View.GONE);
                        btn_logging.setActivated(false);
                        btn_sgnl.setVisibility(View.GONE);

                        plz_wait.playAnimation();
                        plz_wait.setVisibility(View.VISIBLE);
                        firebaseAuth.signInWithEmailAndPassword(e_mail+"@gmail.com",password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("idnum",e_mail);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                plz_wait.pauseAnimation();
                                plz_wait.setVisibility(View.GONE);

                                textView6.setVisibility(View.VISIBLE);
                                btn_logging.setVisibility(View.VISIBLE);
                                btn_sgnl.setVisibility(View.VISIBLE);

                                Toasty.error(getApplicationContext(), "Loging Failed.Try Again...", Toast.LENGTH_SHORT, true).show();

                            }
                        });


                    }

                }
            }
        });











    }
}