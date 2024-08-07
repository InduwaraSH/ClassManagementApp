package com.anandabs.anandabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdmissionRequest extends AppCompatActivity {
    private RecyclerView rv_admision;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<SignModule>RequestList=new ArrayList<>();
    private LottieAnimationView downdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_request);


        rv_admision=findViewById(R.id.rv_admision);
        downdata=findViewById(R.id.downdata);




        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("verifyacc");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                downdata.setVisibility(View.VISIBLE);
                downdata.playAnimation();

                ArrayList<SignModule>RequestList=new ArrayList<>();

                for (DataSnapshot request:snapshot.getChildren()){
                    SignModule signModule= request.getValue(SignModule.class);
                    RequestList.add(signModule);
                }
                downdata.setVisibility(View.VISIBLE);
                downdata.playAnimation();

                AdmissionAdapter admissionAdapter=new AdmissionAdapter(AdmissionRequest.this,RequestList);
                rv_admision.setAdapter(admissionAdapter);
                rv_admision.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                downdata.setVisibility(View.GONE);
                downdata.pauseAnimation();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









    }
}