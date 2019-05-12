package com.iteso.mitutor.tools;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iteso.mitutor.ActivitySubject;
import com.iteso.mitutor.R;
import com.iteso.mitutor.beans.Subject;

import java.util.ArrayList;
import java.util.List;

public class AdapterFavorites extends RecyclerView.Adapter<AdapterFavorites.ViewHolder> {
    private ArrayList<Subject> subjectDataSet;
    private ArrayList<Subject> subjectDataSetFull;
    private Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterFavorites(Context context, ArrayList<Subject> subjectDataSet) {
        this.subjectDataSet = subjectDataSet;
        subjectDataSetFull = new ArrayList<>(subjectDataSet);
        this.context = context;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public AdapterFavorites.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        AdapterFavorites.ViewHolder vh = new AdapterFavorites.ViewHolder(v);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView subjectName;
        public ImageView subjectImg;
        public RelativeLayout mEventLayout;
        public ImageView fav;
        public Context context;

        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
            mCardView = v.findViewById(R.id.favorite_cardview);
            mEventLayout = v.findViewById(R.id.rl_subjects);
            subjectName = v.findViewById(R.id.subject_name);
            subjectImg = v.findViewById(R.id.subject_img);
            fav = v.findViewById(R.id.fav_button);

        }
    }
    @Override
    public void onBindViewHolder(final AdapterFavorites.ViewHolder holder, final int position) {
        holder.subjectName.setText(subjectDataSet.get(position).getSubjectName());
        holder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivitySubject.class);
                intent.putExtra(Constants.SUBJECT,subjectDataSet.get(position));
                context.startActivity(intent);
            }
        });
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fav.setImageResource(R.drawable.ic_favorite_pink);
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


