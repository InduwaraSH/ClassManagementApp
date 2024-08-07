package com.anandabs.anandabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MarksView extends AppCompatActivity {
    private TextView tv_idnum;
    private ImageButton imgbtn_chart;
    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<TestMarksAddModule>mark_list= new ArrayList<>();
    private LottieAnimationView downdata;
    String e_mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_view);


        setContentView(R.layout.activity_marks_view);

        tv_idnum=findViewById(R.id.tv_idnum);
        downdata=findViewById(R.id.downdata);
        imgbtn_chart=findViewById(R.id.imgbtn_chart);

        e_mail=getIntent().getStringExtra("id_num");
        tv_idnum.setText(e_mail);

        imgbtn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),GraphActivity.class);
                intent.putExtra("idNum",e_mail);
                startActivity(intent);
            }
        });


        recyclerView=findViewById(R.id.rv_marks);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Test Result");
        databaseReference.child(e_mail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<TestMarksAddModule>mark_list= new ArrayList<>();

                 MarksAdapter marksAdapter;


                for(DataSnapshot item:snapshot.getChildren()){

                    TestMarksAddModule testMarksAddModule=item.getValue(TestMarksAddModule.class);
                    mark_list.add(testMarksAddModule);

                }
                marksAdapter=new MarksAdapter(MarksView.this,mark_list);
                recyclerView.setAdapter(marksAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                downdata.pauseAnimation();
                downdata.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}