package com.anandabs.anandabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MarksByYear extends AppCompatActivity {
   private ImageButton imgbtn_2023Al,imgbtn_2024Al,imgbtn_2025Al,imgbtn_2026Al;
   String Now_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_by_year);
        Now_year="no";

        imgbtn_2023Al=findViewById(R.id.imgbtn_2023Al);
        imgbtn_2024Al=findViewById(R.id.imgbtn_2024Al);
        imgbtn_2025Al=findViewById(R.id.imgbtn_2025Al);
        imgbtn_2026Al=findViewById(R.id.imgbtn_2026Al);

        imgbtn_2023Al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Now_year= String.valueOf(2023);

                Intent intent=new Intent(getApplicationContext(),badge.class);
                intent.putExtra("Year",Now_year);
                startActivity(intent);


            }
        });

        imgbtn_2024Al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Now_year= String.valueOf(2024);
                Intent intent=new Intent(getApplicationContext(),badge.class);
                intent.putExtra("Year",Now_year);
                startActivity(intent);

            }
        });

        imgbtn_2025Al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Now_year= String.valueOf(2025);
                Intent intent=new Intent(getApplicationContext(),badge.class);
                intent.putExtra("Year",Now_year);
                startActivity(intent);

            }
        });
        imgbtn_2026Al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Now_year= String.valueOf(2026);
                Intent intent=new Intent(getApplicationContext(),badge.class);
                intent.putExtra("Year",Now_year);
                startActivity(intent);

            }
        });







    }
}