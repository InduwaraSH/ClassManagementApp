package com.anandabs.anandabs;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgbtn_zoom, imgbtn_ot, imgbtn_rank, imgbtn_note, imgbtn_controlpanel;
    private Animation fade_anim, y_bottom_toup, upbottom;
    private TextView textView4,tv_name;
    private DatabaseReference databaseReference;

    String Name;




    String get_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        get_email = getIntent().getStringExtra("idnum");
        tv_name=findViewById(R.id.tv_name);

        databaseReference = FirebaseDatabase.getInstance().getReference("User Data");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Name = (snapshot.child(get_email).child("firstName").getValue().toString());
                tv_name.setText(Name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
//                Log.d("call permision", "request permission to send call....");
//                String[] Permissions = {Manifest.permission.CALL_PHONE};
//                requestPermissions(Permissions, 1900);
//            }
//        }
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
//                Log.d("call permision", "request permission to send call....");
//                String[] Permissions = {Manifest.permission.SEND_SMS};
//                requestPermissions(Permissions, 900);
//            }
//        }


        //find ids




        imgbtn_zoom = findViewById(R.id.imgbtn_zoom);
        imgbtn_ot = findViewById(R.id.imgbtn_ot);
        imgbtn_rank = findViewById(R.id.imgbtn_rank);
        imgbtn_note = findViewById(R.id.imgbtn_note);
        //imgbtn_controlpanel = findViewById(R.id.imgbtn_controlpanel);
        textView4 = findViewById(R.id.textView4);




        //animations
        fade_anim = AnimationUtils.loadAnimation(this, R.anim.fade_anim);
        imgbtn_zoom.setAnimation(fade_anim);
        imgbtn_ot.setAnimation(fade_anim);
        imgbtn_rank.setAnimation(fade_anim);
        imgbtn_note.setAnimation(fade_anim);
        //imgbtn_controlpanel.setAnimation(fade_anim);
        textView4.setAnimation(fade_anim);







        y_bottom_toup = AnimationUtils.loadAnimation(this, R.anim.y_bottom_toup);
        upbottom = AnimationUtils.loadAnimation(this, R.anim.upbottom);


//        imgbtn_zoom.setAnimation(y_bottom_toup);
//        imgbtn_ot.setAnimation(y_bottom_toup);
//        imgbtn_rank.setAnimation(y_bottom_toup);
//        imgbtn_note.setAnimation(y_bottom_toup);
//        imgbtn_controlpanel.setAnimation(y_bottom_toup);
        //actions


        imgbtn_ot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.warning(getApplicationContext(), "Beware of the dog.", Toast.LENGTH_SHORT, true).show();
            }
        });

        imgbtn_zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ZoomActivity.class);
                intent.putExtra("Idnum", get_email);
                startActivity(intent);
            }
        });



        imgbtn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://drive.google.com/drive/folders/1hlV3zP8d6Lry1zr0RoLDZAA-D2izA2yv?usp=sharing");


            }
        });


        imgbtn_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MarksView.class);
                intent.putExtra("id_num", get_email);
                startActivity(intent);
            }
        });

//        imgbtn_controlpanel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), ControlPanel.class);
//                startActivity(intent);
//            }
//        });




    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}