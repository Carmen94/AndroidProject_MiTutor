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
import com.iteso.mitutor.beans.Message;

import java.util.ArrayList;

public class AdapterAllChats extends RecyclerView.Adapter<AdapterAllChats.ViewHolder> {
    private ArrayList<Message> messageDataSet;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterAllChats(Context context, ArrayList<Message> chatsDataSet) {
        this.messageDataSet = chatsDataSet;
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
        holder.messageSender.setText(messageDataSet.get(position).getAuthor());
 /*       switch(subjectDataSet.get(position).getSubjectImageUrl()){
            case "":
                holder.subjectThumbnail.setImageResource(R.drawable.ic_launcher_background); break;
            case "0":
                holder.subjectThumbnail.setImageResource(R.drawable.ic_launcher_background); break;
            case "1":
                holder.subjectThumbnail.setImageResource(R.drawable.ic_launcher_background); break;
        }*/
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
                Intent intent = new Intent(context, ActivityChat.class);
                intent.putExtra(Constants.CHAT,messageDataSet.get(position));
                int id = messageDataSet.get(position).getChatID();
                ((ActivityAllChats) context).startActivityForResult(intent, id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageDataSet.size();
    }

}
