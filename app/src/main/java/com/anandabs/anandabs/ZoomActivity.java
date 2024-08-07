package com.anandabs.anandabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Year;

import es.dmoral.toasty.Toasty;

public class ZoomActivity extends AppCompatActivity {
    String get_idNum;
    String Year_get;
    String Year;
    String Year_Noti;
    String Notification_rev;
    String Notification_theory;
    String Notification_theory_show;
    String Notification_rev_show;
    String Link;
    private Dialog dialog;

    private DatabaseReference databaseReference_notice;

    //class exam year
    int this_year = 2023;
    String next_year = String.valueOf(2024);
    String next_two_year = String.valueOf(2025);
    String next_three_year = String.valueOf(2026);

    private ImageButton imgbtn_Theory, imgbtn_Rev;
    private LottieAnimationView rev_class_bell, theory_class_bell, loading_notification;
    private DatabaseReference databaseReference, databaseReference_meeting,databaseReference_notice_bell,databaseReference_notice_bell_msg;

    private TextView Notification_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);



        imgbtn_Theory = findViewById(R.id.imgbtn_Theory);
        imgbtn_Rev = findViewById(R.id.imgbtn_Rev);
        rev_class_bell = findViewById(R.id.rev_class_bell);
        theory_class_bell = findViewById(R.id.theory_class_bell);
        loading_notification = findViewById(R.id.loading_notification);

        //animation and visibility bell off

        theory_class_bell.setVisibility(View.GONE);
        theory_class_bell.pauseAnimation();
        rev_class_bell.setVisibility(View.GONE);
        rev_class_bell.pauseAnimation();


        loading_notification.playAnimation();
        loading_notification.setVisibility(View.VISIBLE);




        get_idNum = getIntent().getStringExtra("Idnum");



        dialog = new Dialog(ZoomActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_dialogbox));
        }

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        Notification_text = dialog.findViewById(R.id.Notification_text);

        databaseReference = FirebaseDatabase.getInstance().getReference("User Data");

        databaseReference_notice_bell = FirebaseDatabase.getInstance().getReference("Notice Link");
        databaseReference_notice_bell_msg = FirebaseDatabase.getInstance().getReference("Notice Link");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loading_notification.playAnimation();
                loading_notification.setVisibility(View.VISIBLE);
                Year_Noti = (snapshot.child(get_idNum).child("year").getValue().toString());


                databaseReference_notice_bell.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Notification_rev = (snapshot.child(Year_Noti + "revision").getValue().toString());
                        Notification_theory = (snapshot.child(Year_Noti + "theory").getValue().toString());
                        if (Notification_rev.isEmpty()) {
                            rev_class_bell.setVisibility(View.GONE);
                            rev_class_bell.pauseAnimation();
                        } else {
                            rev_class_bell.playAnimation();
                            rev_class_bell.setVisibility(View.VISIBLE);
                        }

                        if (Notification_theory.isEmpty()) {
                            theory_class_bell.setVisibility(View.GONE);
                            theory_class_bell.pauseAnimation();
                        } else {
                            theory_class_bell.playAnimation();
                            theory_class_bell.setVisibility(View.VISIBLE);
                        }

                        loading_notification.pauseAnimation();
                        loading_notification.setVisibility(View.GONE);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toasty.error(getApplicationContext(),"Something Went Wrong",Toasty.LENGTH_LONG,true).show();

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toasty.error(getApplicationContext(),"Something Went Wrong",Toasty.LENGTH_LONG,true).show();

            }
        });

        databaseReference_meeting = FirebaseDatabase.getInstance().getReference("Meeting Links");
        imgbtn_Rev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Year = (snapshot.child(get_idNum).child("year").getValue().toString());

                        databaseReference_meeting.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Link = (snapshot.child(Year + "revision").getValue().toString());
                                gotoUrl(Link);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toasty.error(getApplicationContext(),"Something Went Wrong",Toasty.LENGTH_LONG,true).show();

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toasty.error(getApplicationContext(),"Something Went Wrong",Toasty.LENGTH_LONG,true).show();

                    }
                });


            }
        });


        imgbtn_Theory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Year = (snapshot.child(get_idNum).child("year").getValue().toString());

                        databaseReference_meeting.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Link = (snapshot.child(Year + "theory").getValue().toString());
                                gotoUrl(Link);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toasty.error(getApplicationContext(),"Something Went Wrong",Toasty.LENGTH_LONG,true).show();

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toasty.error(getApplicationContext(),"Something Went Wrong",Toasty.LENGTH_LONG,true).show();

                    }
                });

            }
        });



        theory_class_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference_notice_bell_msg.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Notification_theory_show = (snapshot.child(Year_Noti + "theory").getValue().toString());
                        Notification_text.setText(Notification_theory_show);
                        dialog.show();
                        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                Notification_theory_show="";
                                Notification_text.setText(Notification_rev_show);
                                dialog.show();
                                dialog.hide();

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toasty.error(getApplicationContext(),"Something Went Wrong",Toasty.LENGTH_LONG,true).show();

                    }
                });

            }
        });

        rev_class_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference_notice_bell_msg.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Notification_rev_show = (snapshot.child(Year_Noti + "revision").getValue().toString());
                        Notification_text.setText(Notification_rev_show);
                        dialog.show();
                        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                Notification_rev_show="";
                                Notification_text.setText(Notification_rev_show);
                                dialog.show();
                                dialog.hide();

                            }
                        });



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toasty.error(getApplicationContext(),"Something Went Wrong",Toasty.LENGTH_LONG,true).show();

                    }
                });

            }
        });




    }


    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

}

