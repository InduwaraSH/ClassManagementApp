package com.anandabs.anandabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView textView2;
    String asd;
//    private Integer plus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView2=findViewById(R.id.textView2);

        databaseReference=FirebaseDatabase.getInstance().getReference("testing");
        databaseReference.child("kamal").setValue(50);

        databaseReference.child("kamal").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot item= (DataSnapshot) snapshot.getValue();

                   textView2.setText((CharSequence) item);







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }
}