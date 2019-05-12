package com.iteso.mitutor.tools;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iteso.mitutor.ActivitySubject;
import com.iteso.mitutor.R;
import com.iteso.mitutor.beans.Subject;

import java.util.ArrayList;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {

    public Context c;
    public ArrayList<Subject> arrayList;

    public AdapterSearch(Context c, ArrayList<Subject> arrayList){
        this.c = c;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_light_subject, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Subject subject = arrayList.get(i);

        viewHolder.t1.setText(subject.getSubjectName());
      /*  viewHolder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivitySubject.class);
                intent.putExtra(Constants.SUBJECT,subjectDataSet.get(position));
                context.startActivity(intent);
            }
        });
        switch(subject.get(i).getSubjectId()){
            case "1":
                viewHolder.i1.setImageResource(R.drawable.graph); break;
            case "2":
                viewHolder.i1.setImageResource(R.drawable.calculator); break;
            case "3":
                viewHolder.i1.setImageResource(R.drawable.function); break;
            case "4":
                viewHolder.i1.setImageResource(R.drawable.data); break;
        }//*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView t1;
        public ImageView i1;
        public RelativeLayout mEventLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.subject_name);
            i1 = (ImageView) itemView.findViewById(R.id.subject_img);
            mEventLayout = itemView.findViewById(R.id.rl_subjects);
        }
    }
}
