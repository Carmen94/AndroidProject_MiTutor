package com.iteso.mitutor.tools;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.mitutor.R;

public class AllSubjectsViewHolder extends RecyclerView.ViewHolder {

    public TextView t1;
    public ImageView i1;

    public AllSubjectsViewHolder(View itemView) {
        super(itemView);

        t1 = (TextView) itemView.findViewById(R.id.subject_name);
        i1 = (ImageView) itemView.findViewById(R.id.subject_img);
    }
}
