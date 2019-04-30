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

import com.iteso.mitutor.ActivityMain;
import com.iteso.mitutor.ActivitySubject;
import com.iteso.mitutor.R;
import com.iteso.mitutor.beans.Tutoring;


import java.util.ArrayList;

public class AdapterTutorings extends RecyclerView.Adapter<AdapterTutorings.ViewHolder> {
    private ArrayList<Tutoring> tutoringsDataSet;
    private Context context;
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterTutorings(Context context, ArrayList<Tutoring> tutoringsDataSet) {
        this.tutoringsDataSet = tutoringsDataSet;
        this.context = context;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public AdapterTutorings.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button mDetail;
        public CardView mCardView;
        public TextView tutoringTitle;
        public TextView subjectTutor;
        public TextView subjectLocation;
        public TextView subjectPhone;
        public RelativeLayout mEventLayout;
        public ImageView subjectThumbnail;
        public Context context;

        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mEventLayout = (RelativeLayout) v.findViewById(R.id.item_subject_layout);
            tutoringTitle = (TextView) v.findViewById(R.id.item_subject_title);
            subjectTutor = (TextView) v.findViewById(R.id.item_subject_tutor);
            subjectLocation = (TextView) v.findViewById(R.id.item_subject_location);
            subjectPhone = (TextView) v.findViewById(R.id.item_product_phone);
            subjectThumbnail = (ImageView) v.findViewById(R.id.item_subject_thumbnail);
        }
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tutoringTitle.setText(tutoringsDataSet.get(position).getDescription());
        holder.subjectTutor.setText(tutoringsDataSet.get(position).getTutor().getFirstName());
        holder.subjectLocation.setText(tutoringsDataSet.get(position).getLocation());
        holder.subjectPhone.setText(tutoringsDataSet.get(position).getTutor().getPhone());
        switch(tutoringsDataSet.get(position).getImage()){
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
                intent.putExtra(Constants.SUBJECT,tutoringsDataSet.get(position).getSubject());
                ((ActivityMain) context).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tutoringsDataSet.size();
    }

}