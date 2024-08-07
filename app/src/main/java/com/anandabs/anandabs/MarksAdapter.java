package com.anandabs.anandabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MarksAdapter extends RecyclerView.Adapter<MarksAdapter.ViewHolder> {
    private Context m_context;
    private ArrayList<TestMarksAddModule> mark_list;
    private LayoutInflater inflater;

    public MarksAdapter(Context m_context, ArrayList<TestMarksAddModule> mark_list) {
        this.m_context = m_context;
        this.mark_list = mark_list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater=LayoutInflater.from(m_context);
        View view=inflater.inflate(R.layout.markslist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_testno.setText(mark_list.get(position).test_num);
        holder.tv_marks.setText(mark_list.get(position).marks);
        if(Integer.parseInt(mark_list.get(position).marks)>75){
            holder.tv_grade.setText("A");
        }
        else {
            if(Integer.parseInt(mark_list.get(position).marks)>65){
                holder.tv_grade.setText("B");
            }
            else {
                if(Integer.parseInt(mark_list.get(position).marks)>50){
                    holder.tv_grade.setText("C");
                }
                else {
                    if(Integer.parseInt(mark_list.get(position).marks)>35){
                        holder.tv_grade.setText("S");
                    }
                    else {
                        holder.tv_grade.setText("F");
                    }

                }

            }

        }
    }

    @Override
    public int getItemCount() {
        return mark_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_rect;
        private TextView tv_testno,tv_marks,tv_grade,textView18;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_rect=itemView.findViewById(R.id.iv_rect);
            tv_testno=itemView.findViewById(R.id.tv_testno);
            tv_marks=itemView.findViewById(R.id.tv_marks);
            tv_grade=itemView.findViewById(R.id.tv_grade);



        }
    }
}
