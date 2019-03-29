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
import android.widget.Toast;

import com.iteso.mitutor.ActivityMain;
import com.iteso.mitutor.ActivitySubject;
import com.iteso.mitutor.R;
import com.iteso.mitutor.beans.Subject;


import java.util.ArrayList;

public class AdapterSubject extends RecyclerView.Adapter<AdapterSubject.ViewHolder> {
    private ArrayList<Subject> subjectDataSet;
    private Context context;
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterSubject(Context context, ArrayList<Subject> subjectDataSet) {
        this.subjectDataSet = subjectDataSet;
        this.context = context;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public AdapterSubject.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button mDetail;
        public CardView mCardView;
        public TextView subjectTitle;
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
//            mDetail = (Button) v.findViewById(R.id.item_product_detail);
            subjectTitle = (TextView) v.findViewById(R.id.item_subject_title);
            subjectTutor = (TextView) v.findViewById(R.id.item_subject_tutor);
            subjectLocation = (TextView) v.findViewById(R.id.item_subject_location);
            subjectPhone = (TextView) v.findViewById(R.id.item_product_phone);
            subjectThumbnail = (ImageView) v.findViewById(R.id.item_subject_thumbnail);
        }
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.subjectTitle.setText(subjectDataSet.get(position).getSubjectName());
        holder.subjectTutor.setText(subjectDataSet.get(position).getTutorName());
        holder.subjectLocation.setText(subjectDataSet.get(position).getTuitionLocation());
        holder.subjectPhone.setText(subjectDataSet.get(position).getTutorPhone());
        switch(subjectDataSet.get(position).getSubjectImageUrl()){
            case "":
                holder.subjectThumbnail.setImageResource(R.drawable.ic_launcher_background); break;
            case "0":
                holder.subjectThumbnail.setImageResource(R.drawable.ic_launcher_background); break;
            case "1":
                holder.subjectThumbnail.setImageResource(R.drawable.ic_launcher_background); break;
        }
//        holder.mDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, subjectDataSet.get(position).toString(),
//                        Toast.LENGTH_LONG).show();
//            }
//        });
        holder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivitySubject.class);
                intent.putExtra(Constants.SUBJECT,subjectDataSet.get(position));
                int id = subjectDataSet.get(position).getId();
                ((ActivityMain) context).startActivityForResult(intent, id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectDataSet.size();
    }

}