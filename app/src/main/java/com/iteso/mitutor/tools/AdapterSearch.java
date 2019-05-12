package com.iteso.mitutor.tools;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> implements Filterable {
    private ArrayList<Subject> subjectDataSet;
    private ArrayList<Subject> subjectDataSetFull;
    private Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterSearch(Context context, ArrayList<Subject> subjectDataSet) {
        this.subjectDataSet = subjectDataSet;
        subjectDataSetFull = new ArrayList<>(subjectDataSet);
        this.context = context;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public AdapterSearch.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_light_subject, parent, false);
        AdapterSearch.ViewHolder vh = new AdapterSearch.ViewHolder(v);
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
    public void onBindViewHolder(final AdapterSearch.ViewHolder holder, final int position) {
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
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Subject> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length()== 0){
                filteredList.addAll(subjectDataSetFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Subject subject: subjectDataSetFull) {
                    if(subject.getSubjectName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(subject);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            subjectDataSet.clear();
            subjectDataSet.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
