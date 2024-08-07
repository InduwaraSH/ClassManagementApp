package com.anandabs.anandabs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdmissionAdapter extends RecyclerView.Adapter<AdmissionAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SignModule> RequestList;
    private LayoutInflater layoutInflater;

    public AdmissionAdapter(Context context, ArrayList<SignModule> requestList) {
        this.context = context;
        RequestList = requestList;
    }

    @NonNull
    @Override
    public AdmissionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.reqitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdmissionAdapter.ViewHolder holder, int position) {


        holder.tv_id.setText(RequestList.get(position).id_num);
        holder.tv_u_name.setText(RequestList.get(position).name_user_last);

        holder.tv_mobnum.setText(RequestList.get(position).mob_num);
        holder.tv_alyear.setText(RequestList.get(position).year);

        String get_pa =holder.get_password.replace("No",RequestList.get(position).password);
        String get_Id =holder.get_idNum.replace("No",RequestList.get(position).id_num);
        String get_Name =holder.get_Name.replace("No",RequestList.get(position).name_user_last);
        String get_Mobnum =holder.get_Mobnumber.replace("No",RequestList.get(position).mob_num);
        String get_Year =holder.get_alyear.replace("No",RequestList.get(position).year);
        String get_firstName =holder.get_alyear.replace("No",RequestList.get(position).name_user_first);




        holder.img_btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.firebaseAuth.createUserWithEmailAndPassword(get_Id+"@gmail.com",get_pa).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                   public void onSuccess(AuthResult authResult) {
                        holder.databaseReference=FirebaseDatabase.getInstance().getReference("User Data");
                        yearModule yearmodule_userdata=new yearModule(get_Name,get_Id,get_Year,get_Mobnum,get_firstName);
                        holder.databaseReference.child(get_Id).setValue(yearmodule_userdata);

                        holder.databaseReference=FirebaseDatabase.getInstance().getReference(get_Year + "Advanced Level");
                        yearModule yearmodule=new yearModule(get_Name,get_Id,get_Year,get_Mobnum,get_firstName);
                        holder.databaseReference.child(get_Name).setValue(yearmodule);

                        holder.databaseReference=FirebaseDatabase.getInstance().getReference("verifyacc");
                        holder.databaseReference.child(get_Id).removeValue();


                       Toasty.success(context.getApplicationContext(), "Successfully Registrd",Toasty.LENGTH_LONG).show();
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toasty.error(context.getApplicationContext(), "Failed",Toasty.LENGTH_LONG).show();

                   }
               });
            }
        });

        holder.img_btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("verifyacc").child(get_Id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toasty.success(context.getApplicationContext(), "Deleted",Toasty.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toasty.error(context.getApplicationContext(), "Failed",Toasty.LENGTH_LONG).show();
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return RequestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView5;
        private ImageButton img_btn_reject,img_btn_accept;
        private TextView tv_id,tv_u_name,tv_mobnum,tv_alyear;
        private FirebaseDatabase firebaseDatabase;
        private DatabaseReference databaseReference;
        private FirebaseAuth firebaseAuth;

        String get_idNum,get_password,get_Name,get_Mobnumber,get_alyear,get_firstName;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView5=itemView.findViewById(R.id.imageView5);
            img_btn_reject=itemView.findViewById(R.id.img_btn_reject);
            img_btn_accept=itemView.findViewById(R.id.img_btn_accept);
            tv_id=itemView.findViewById(R.id.tv_id);
            tv_u_name=itemView.findViewById(R.id.tv_u_name);
            tv_mobnum=itemView.findViewById(R.id.tv_mobnum);
            tv_alyear=itemView.findViewById(R.id.tv_alyear);


            firebaseAuth=FirebaseAuth.getInstance();


            get_idNum="No";
            get_password="No";
            get_Name="No";
            get_Mobnumber="No";
            get_alyear="No";
            get_firstName="No";


        }
    }
}
