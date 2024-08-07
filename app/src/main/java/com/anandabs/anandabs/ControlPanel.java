package com.anandabs.anandabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class ControlPanel extends AppCompatActivity {
    private ImageButton imgbtn_addmarks,imgbtn_userrequest,img_btn_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);

        setContentView(R.layout.activity_control_panel);

        imgbtn_userrequest=findViewById(R.id.imgbtn_userrequest);
        imgbtn_addmarks=findViewById(R.id.imgbtn_addmarks);
        img_btn_link=findViewById(R.id.img_btn_link);

        imgbtn_addmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MarksByYear.class);
                startActivity(intent);
            }
        });

        imgbtn_userrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdmissionRequest.class);
                startActivity(intent);
            }
        });

        img_btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ZoomLinkUplod.class);
               startActivity(intent);


            }
        });












    }
    
}