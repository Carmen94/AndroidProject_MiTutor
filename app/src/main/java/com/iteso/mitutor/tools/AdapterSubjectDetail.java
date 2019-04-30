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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.mitutor.ActivityChat;
import com.iteso.mitutor.ActivityMain;
import com.iteso.mitutor.ActivitySubject;
import com.iteso.mitutor.R;
import com.iteso.mitutor.beans.Subject;
import com.iteso.mitutor.beans.Tutoring;


import java.util.ArrayList;

public class AdapterSubjectDetail extends RecyclerView.Adapter<AdapterSubjectDetail.ViewHolder> {
    private ArrayList<Tutoring> tutorings;
    private Context context;
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterSubjectDetail(Context context, ArrayList<Tutoring> tutorings) {
        this.tutorings = tutorings;
        this.context = context;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public AdapterSubjectDetail.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject_detail, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button schedule_button;
        public CardView mCardView;
        public TextView subjectTutor;
        public TextView subjectScore;
        public TextView subjectPhone;
        public TextView subjectLocation;
        public TextView subjectDescription;
        public TextView subject_detail_score_label, subject_detail_description_label,subject_detail_location_label,subject_detail_phone_label;
        public LinearLayout mEventLayout;
        public ImageView subjectThumbnail;

        public Context context;

        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
            mCardView = v.findViewById(R.id.card_view);
            mEventLayout = v.findViewById(R.id.item_subject_detail);
            subjectScore = v.findViewById(R.id.item_subject_detail_score);
            subjectPhone = v.findViewById(R.id.item_subject_detail_phone);
            subjectTutor = v.findViewById(R.id.item_subject_detail_tutor);
            subjectLocation = v.findViewById(R.id.item_subject_detail_location);
            subjectThumbnail = v.findViewById(R.id.item_subject_detail_thumbnail);
            subjectDescription =  v.findViewById(R.id.item_subject_detail_description);
            schedule_button = v.findViewById(R.id.schedule_button);

            subject_detail_score_label = v.findViewById(R.id.subject_detail_score_label);
            subject_detail_phone_label = v.findViewById(R.id.subject_detail_phone_label);
            subject_detail_location_label = v.findViewById(R.id.subject_detail_location_label);
            subject_detail_description_label = v.findViewById(R.id.subject_detail_description_label);

            subject_detail_score_label.setText(R.string.score);
            subject_detail_phone_label.setText(R.string.phone);
            subject_detail_location_label.setText(R.string.location);
            subject_detail_description_label.setText(R.string.description);
        }
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.subjectScore.setText(tutorings.get(position).getScore()+"");
        holder.subjectTutor.setText(tutorings.get(position).getTutor().getFirstName());
        holder.subjectLocation.setText(tutorings.get(position).getLocation());
        holder.subjectPhone.setText(tutorings.get(position).getTutor().getPhone());
        holder.subjectDescription.setText(tutorings.get(position).getDescription());
        switch(tutorings.get(position).getImage()){
            case 0:
                holder.subjectThumbnail.setImageResource(R.drawable.ic_launcher_background); break;
            case 1:
                holder.subjectThumbnail.setImageResource(R.drawable.ic_launcher_background); break;
            case 2:
                holder.subjectThumbnail.setImageResource(R.drawable.ic_launcher_background); break;
        }//
        holder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivitySubject.class);
                intent.putExtra(Constants.SUBJECT,tutorings.get(position));
                ((ActivitySubject) context).startActivity(intent);
            }
        });
        holder.schedule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ActivityChat.class);
                intent.putExtra(Constants.TUTORING,tutorings.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tutorings.size();
    }

}