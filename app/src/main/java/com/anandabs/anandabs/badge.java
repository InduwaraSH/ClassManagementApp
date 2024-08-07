package com.anandabs.anandabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class badge extends AppCompatActivity {
    private TextView tv_year_num_topic;
    private RecyclerView rv_badge;
    private DatabaseReference databaseReference_badge;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<yearModule>batch_list=new ArrayList<>();
    private LottieAnimationView loding;
    String get_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);

        rv_badge=findViewById(R.id.rv_badge);
        loding=findViewById(R.id.loding);
        tv_year_num_topic=findViewById(R.id.tv_year_num_topic);
        get_year=getIntent().getStringExtra("Year");
        tv_year_num_topic.setText(get_year+" Group");



        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference_badge=firebaseDatabase.getReference(get_year+"Advanced Level");
        databaseReference_badge.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                badgeAdapter badgeadapter;
                ArrayList<yearModule>batch_list=new ArrayList<>();

                for(DataSnapshot children:snapshot.getChildren()){
                    yearModule yearmodule= children.getValue(yearModule.class);
                    batch_list.add(yearmodule);
                }

                badgeadapter=new badgeAdapter(badge.this,batch_list);
                rv_badge.setAdapter(badgeadapter);
                rv_badge.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                loding.setVisibility(View.GONE);
                loding.pauseAnimation();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }
}