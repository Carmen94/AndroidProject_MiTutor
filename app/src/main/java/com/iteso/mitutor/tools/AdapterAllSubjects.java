package com.iteso.mitutor.tools;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iteso.mitutor.ActivityAllSubjects;
import com.iteso.mitutor.ActivityMain;
import com.iteso.mitutor.ActivitySubject;
import com.iteso.mitutor.R;
import com.iteso.mitutor.beans.Subject;

import java.util.ArrayList;

public class AdapterAllSubjects extends RecyclerView.Adapter<AdapterAllSubjects.ViewHolder> {
    private ArrayList<Subject> subjectDataSet;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterAllSubjects(Context context, ArrayList<Subject> subjectDataSet) {
        this.subjectDataSet = subjectDataSet;
        this.context = context;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public AdapterAllSubjects.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_light_subject, parent, false);
        AdapterAllSubjects.ViewHolder vh = new AdapterAllSubjects.ViewHolder(v);
        return vh;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView subjectName;
        public ImageView subjectImg;
        public RelativeLayout mEventLayout;
        public Context context;

        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
            mCardView = v.findViewById(R.id.cardview);
            mEventLayout = v.findViewById(R.id.rl_subjects);
            subjectName = v.findViewById(R.id.subject_name);
            subjectImg = v.findViewById(R.id.subject_img);
        }
    }


    @Override
    public void onBindViewHolder(final AdapterAllSubjects.ViewHolder holder, final int position) {
        holder.subjectName.setText(subjectDataSet.get(position).getSubjectName());
        holder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivitySubject.class);
                intent.putExtra(Constants.SUBJECT,subjectDataSet.get(position));
                context.startActivity(intent);
            }
        });
        switch(subjectDataSet.get(position).getSubjectId()){
            case "1":
                holder.subjectImg.setImageResource(R.drawable.graph); break;
            case "2":
                holder.subjectImg.setImageResource(R.drawable.calculator); break;
            case "3":
                holder.subjectImg.setImageResource(R.drawable.function); break;
            case "4":
                holder.subjectImg.setImageResource(R.drawable.data); break;
        }//
    }

    @Override
    public int getItemCount() {
        return subjectDataSet.size();
    }

}
