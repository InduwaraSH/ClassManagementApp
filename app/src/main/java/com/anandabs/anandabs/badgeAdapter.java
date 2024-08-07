package com.anandabs.anandabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class badgeAdapter extends RecyclerView.Adapter<badgeAdapter.ViewHolder> {
    private badge new_context;
    private ArrayList<yearModule> batch_list;
    private LayoutInflater layoutInflater;


    public badgeAdapter(badge new_context, ArrayList<yearModule> batch_list) {
        this.new_context = new_context;
        this.batch_list = batch_list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(new_context);
        View view=layoutInflater.inflate(R.layout.badgeitem,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_namebadge.setText(batch_list.get(position).name_user);



        String idNum=holder.get_idnum.replace("No",batch_list.get(position).id_num);
        holder.imgbtn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toasty.warning(new_context.getApplicationContext(),c,Toasty.LENGTH_LONG).show();
                String t_Number= String.valueOf(holder.et_testNo.getText());
                String t_marks= String.valueOf(holder.et_testMarks.getText());
                if (t_Number.isEmpty()){
                    Toasty.warning(new_context.getApplicationContext(), "Enter Test Number",Toasty.LENGTH_LONG).show();
                }
                else {
                    if (t_marks.isEmpty()){
                        Toasty.warning(new_context.getApplicationContext(),"Enter Test Marks",Toasty.LENGTH_LONG).show();
                    }
                    else {
                        holder.databaseReference=holder.firebaseDatabase.getReference("Test Result");
                        TestMarksAddModule testMarksAddModule=new TestMarksAddModule(idNum,t_marks,t_Number);
                        holder.databaseReference.child(idNum).child(testMarksAddModule.test_num).setValue(testMarksAddModule).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toasty.success(new_context.getApplicationContext(),"Done",Toasty.LENGTH_LONG).show();
                                holder.et_testNo.setText("");
                                holder.et_testMarks.setText("");
                                holder.et_testMarks.setEnabled(false);
                                holder.et_testNo.setEnabled(false);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toasty.error(new_context.getApplicationContext(),"Failed",Toasty.LENGTH_LONG).show();
                            }
                        });



                    }
                }
            }
        });
        holder.imgbtn_mark_onebyone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(new_context.getApplicationContext(),MarksView.class);
                intent.putExtra("id_num",idNum);
                v.getContext().startActivity(intent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return batch_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imgbtn_submit;
        private ImageButton imgbtn_mark_onebyone;
        private EditText et_testNo,et_testMarks;
        private TextView tv_namebadge;
        private DatabaseReference databaseReference;
        private FirebaseDatabase firebaseDatabase;
        String get_et_testNo,get_et_testMarks,get_idnum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbtn_submit=itemView.findViewById(R.id.imgbtn_submit);
            imgbtn_mark_onebyone=itemView.findViewById(R.id.imgbtn_mark_onebyone);
            et_testNo=itemView.findViewById(R.id.et_testNo);
            et_testMarks=itemView.findViewById(R.id.et_testMarks);
            tv_namebadge=itemView.findViewById(R.id.tv_namebadge);
            get_idnum="No";

            firebaseDatabase=FirebaseDatabase.getInstance();
//            databaseReference=firebaseDatabase.getReference(get_idnum);











        }
    }
}