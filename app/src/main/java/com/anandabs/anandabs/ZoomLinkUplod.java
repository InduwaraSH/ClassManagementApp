package com.anandabs.anandabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class ZoomLinkUplod extends AppCompatActivity {

    private LottieAnimationView animation_bell_one, animation_bell_two, animation_bell_three, animation_bell_four, animation_notifi, one_dot, two_dot, three_dot, four_dot, rev_dot, theory_dot;
    private ImageButton img_btn_one, img_btn_two, img_btn_three, img_btn_four, img_btn_theory, img_btn_rev, img_btn_noti, img_btn_stopnotification, img_btn_creatmeeting;
    private EditText et_link_or_notice;

    private String selected_year = "No";

    private String Noti_four_value_revision, Noti_four_value_theory, Noti_three_value_theory, Noti_three_value_revision, Noti_two_value_revision, Noti_two_value_theory, Noti_one_value_revision, Noti_one_value_theory;

    private String one_value = String.valueOf(2023);
    private String two_value = String.valueOf(2024);
    private String three_value = String.valueOf(2025);
    private String four_value = String.valueOf(2026);
    private String Type = "No";
    private String text;
    private DatabaseReference databaseReference_notice, databaseReference_meeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_link_uplod);

        databaseReference_notice = FirebaseDatabase.getInstance().getReference("Notice Link");
        databaseReference_meeting = FirebaseDatabase.getInstance().getReference("Meeting Links");


        //animation bell
        animation_bell_one = findViewById(R.id.animation_bell_one);
        animation_bell_two = findViewById(R.id.animation_bell_two);
        animation_bell_three = findViewById(R.id.animation_bell_three);
        animation_bell_four = findViewById(R.id.animation_bell_four);
        animation_notifi = findViewById(R.id.animation_notifi);

        //animation dot
        one_dot = findViewById(R.id.one_dot);
        two_dot = findViewById(R.id.two_dot);
        three_dot = findViewById(R.id.three_dot);
        four_dot = findViewById(R.id.four_dot);

        //animation rev & theory
        rev_dot = findViewById(R.id.rev_dot);
        theory_dot = findViewById(R.id.theory_dot);

        //img btn year selector
        img_btn_one = findViewById(R.id.img_btn_one);
        img_btn_two = findViewById(R.id.img_btn_two);
        img_btn_three = findViewById(R.id.img_btn_three);
        img_btn_four = findViewById(R.id.img_btn_four);

        //img btn rev& theory
        img_btn_rev = findViewById(R.id.img_btn_rev);
        img_btn_theory = findViewById(R.id.img_btn_theory);

        //img btn operations
        img_btn_noti = findViewById(R.id.img_btn_noti);
        img_btn_stopnotification = findViewById(R.id.img_btn_stopnotification);
        img_btn_creatmeeting = findViewById(R.id.img_btn_creatmeeting);

        //img btn textpad
        et_link_or_notice = findViewById(R.id.et_link_or_notice);

        animation_bell_one.setVisibility(View.GONE);
        animation_bell_two.setVisibility(View.GONE);
        animation_bell_three.setVisibility(View.GONE);
        animation_bell_four.setVisibility(View.GONE);
        animation_bell_four.setVisibility(View.GONE);
        one_dot.setVisibility(View.GONE);
        two_dot.setVisibility(View.GONE);
        three_dot.setVisibility(View.GONE);
        four_dot.setVisibility(View.GONE);
        rev_dot.setVisibility(View.GONE);
        theory_dot.setVisibility(View.GONE);


        img_btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one_dot.playAnimation();
                one_dot.setVisibility(View.VISIBLE);

                two_dot.setVisibility(View.GONE);
                three_dot.setVisibility(View.GONE);
                four_dot.setVisibility(View.GONE);

                selected_year = one_value;

            }
        });

        img_btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                two_dot.playAnimation();
                two_dot.setVisibility(View.VISIBLE);

                one_dot.setVisibility(View.GONE);
                three_dot.setVisibility(View.GONE);
                four_dot.setVisibility(View.GONE);

                selected_year = two_value;

            }
        });

        img_btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                three_dot.playAnimation();
                three_dot.setVisibility(View.VISIBLE);

                one_dot.setVisibility(View.GONE);
                two_dot.setVisibility(View.GONE);
                four_dot.setVisibility(View.GONE);

                selected_year = three_value;

            }
        });

        img_btn_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                four_dot.playAnimation();
                four_dot.setVisibility(View.VISIBLE);

                one_dot.setVisibility(View.GONE);
                three_dot.setVisibility(View.GONE);
                three_dot.setVisibility(View.GONE);

                selected_year = four_value;

            }
        });

        img_btn_rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rev_dot.playAnimation();
                rev_dot.setVisibility(View.VISIBLE);
                theory_dot.setVisibility(View.GONE);
                Type = "revision";

            }
        });

        img_btn_theory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theory_dot.playAnimation();
                theory_dot.setVisibility(View.VISIBLE);
                rev_dot.setVisibility(View.GONE);
                Type = "theory";
            }
        });

        img_btn_creatmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text = et_link_or_notice.getText().toString();
                if (selected_year == "No") {
                    Toasty.warning(getApplicationContext(), "Tap Exam Year", Toasty.LENGTH_LONG, true).show();
                } else {
                    if (Type == "No") {
                        Toasty.warning(getApplicationContext(), "Tap Revision or Theory", Toasty.LENGTH_LONG, true).show();
                    } else {
                        if (text.isEmpty()) {
                            Toasty.warning(getApplicationContext(), "Enter Meeting Invitation Link", Toasty.LENGTH_LONG, true).show();
                        } else {
                            databaseReference_meeting.child(selected_year + Type).setValue(text).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toasty.success(getApplicationContext(), selected_year + Type + " Meeting Created", Toasty.LENGTH_LONG, true).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toasty.success(getApplicationContext(), "Failed.Try Again...", Toasty.LENGTH_LONG, true).show();
                                }
                            });
                        }
                    }
                }
            }
        });


        img_btn_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (selected_year == "No") {
                    Toasty.warning(getApplicationContext(), "Tap Exam Year", Toasty.LENGTH_LONG, true).show();
                } else {
                    if (Type == "No") {
                        Toasty.warning(getApplicationContext(), "Tap Revision or Theory", Toasty.LENGTH_LONG, true).show();
                    } else {

                        text = et_link_or_notice.getText().toString();
                        databaseReference_notice.child(selected_year + Type).setValue(text).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toasty.success(getApplicationContext(), selected_year + Type + " Notification Created", Toasty.LENGTH_LONG, true).show();
                                animation_notifi.playAnimation();
                                et_link_or_notice.setText("");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toasty.success(getApplicationContext(), selected_year + Type + " Try Again", Toasty.LENGTH_LONG, true).show();
                            }
                        });

                    }
                }
            }


        });

        img_btn_stopnotification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_year == "No") {
                    Toasty.warning(getApplicationContext(), "Tap Exam Year", Toasty.LENGTH_LONG, true).show();
                } else {
                    if (Type == "No") {
                        Toasty.warning(getApplicationContext(), "Tap Revision or Theory", Toasty.LENGTH_LONG, true).show();
                    } else {
                        databaseReference_notice.child(selected_year + Type).setValue("");
                        Toasty.success(getApplicationContext(), selected_year + Type + " Notification Disabled", Toasty.LENGTH_LONG, true).show();

                    }
                }
            }
        });

        databaseReference_notice.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Noti_one_value_theory = (snapshot.child(one_value + "theory").getValue().toString());
                Noti_one_value_revision = (snapshot.child(one_value + "revision").getValue().toString());
                Noti_two_value_theory = (snapshot.child(two_value + "theory").getValue().toString());
                Noti_two_value_revision = (snapshot.child(two_value + "revision").getValue().toString());
                Noti_three_value_theory = (snapshot.child(three_value + "theory").getValue().toString());
                Noti_three_value_revision = (snapshot.child(three_value + "revision").getValue().toString());
                Noti_four_value_theory = (snapshot.child(four_value + "theory").getValue().toString());
                Noti_four_value_revision = (snapshot.child(four_value + "revision").getValue().toString());
// bell one animation
                if (Noti_one_value_theory.isEmpty()) {
                    animation_bell_one.pauseAnimation();
                    animation_bell_one.setVisibility(View.GONE);
                    if (Noti_one_value_revision.isEmpty()) {
                        animation_bell_one.pauseAnimation();
                        animation_bell_one.setVisibility(View.GONE);
                    } else {
                        animation_bell_one.playAnimation();
                        animation_bell_one.setVisibility(View.VISIBLE);
                    }
                } else {
                    animation_bell_one.playAnimation();
                    animation_bell_one.setVisibility(View.VISIBLE);

                }
// bell two animation

                if (Noti_two_value_theory.isEmpty()) {
                    animation_bell_two.pauseAnimation();
                    animation_bell_two.setVisibility(View.GONE);
                    if (Noti_two_value_revision.isEmpty()) {
                        animation_bell_two.pauseAnimation();
                        animation_bell_two.setVisibility(View.GONE);
                    } else {
                        animation_bell_two.playAnimation();
                        animation_bell_two.setVisibility(View.VISIBLE);
                    }
                } else {
                    animation_bell_two.playAnimation();
                    animation_bell_two.setVisibility(View.VISIBLE);

                }
// bell three animation
                if (Noti_three_value_theory.isEmpty()) {
                    animation_bell_three.pauseAnimation();
                    animation_bell_three.setVisibility(View.GONE);
                    if (Noti_three_value_revision.isEmpty()) {
                        animation_bell_three.pauseAnimation();
                        animation_bell_three.setVisibility(View.GONE);
                    } else {
                        animation_bell_three.playAnimation();
                        animation_bell_three.setVisibility(View.VISIBLE);
                    }
                } else {
                    animation_bell_three.playAnimation();
                    animation_bell_three.setVisibility(View.VISIBLE);

                }
// bell four animation
                if (Noti_four_value_theory.isEmpty()) {
                    animation_bell_four.pauseAnimation();
                    animation_bell_four.setVisibility(View.GONE);
                    if (Noti_four_value_revision.isEmpty()) {
                        animation_bell_four.pauseAnimation();
                        animation_bell_four.setVisibility(View.GONE);
                    } else {
                        animation_bell_four.playAnimation();
                        animation_bell_four.setVisibility(View.VISIBLE);
                    }
                } else {
                    animation_bell_four.playAnimation();
                    animation_bell_four.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}