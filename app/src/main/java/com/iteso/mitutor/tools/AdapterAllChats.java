package com.iteso.mitutor.tools;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iteso.mitutor.ActivityAllChats;
import com.iteso.mitutor.ActivityChat;
import com.iteso.mitutor.R;
import com.iteso.mitutor.beans.Chat;

import java.util.ArrayList;

public class AdapterAllChats extends RecyclerView.Adapter<AdapterAllChats.ViewHolder> {
    private ArrayList<Chat> chatsDataSet;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterAllChats(Context context, ArrayList<Chat> chatsDataSet) {
        this.chatsDataSet = chatsDataSet;
        this.context = context;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public AdapterAllChats.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_light_message, parent, false);
        AdapterAllChats.ViewHolder vh = new AdapterAllChats.ViewHolder(v);
        return vh;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView messageSender;
        public ImageView messageImage;
        public RelativeLayout mEventLayout;
        public Context context;

        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
            mCardView = (CardView) v.findViewById(R.id.message_cardview);
            mEventLayout = (RelativeLayout) v.findViewById(R.id.relative_messages);
            messageSender = (TextView) v.findViewById(R.id.item_message_name);
            messageImage = (ImageView) v.findViewById(R.id.chat_profile_img);
        }
    }


    @Override
    public void onBindViewHolder(final AdapterAllChats.ViewHolder holder, final int position) {
        holder.messageSender.setText(chatsDataSet.get(position).getTutor().getFirstName()+" "+chatsDataSet.get(position).getTutor().getLastName());
        holder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityChat.class);
                intent.putExtra(Constants.CHAT,chatsDataSet.get(position));
                ((ActivityAllChats) context).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatsDataSet.size();
    }

}
