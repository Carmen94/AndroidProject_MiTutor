package com.iteso.mitutor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.mitutor.beans.Chat;
import com.iteso.mitutor.beans.ChatMessage;
import com.iteso.mitutor.beans.Tutoring;
import com.iteso.mitutor.beans.User;
import com.iteso.mitutor.tools.Constants;


public class ActivityChat extends AppCompatActivity {
    Chat chat;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    Tutoring tutoring;
    String chatKey;
    FloatingActionButton sendButton;
    ListView listOfMessages;
    EditText input;
    private FirebaseListAdapter<ChatMessage> chatAdapter;
    DatabaseReference databaseReference;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        setContentView(R.layout.activity_chat);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference chatReference = databaseReference.child("chats");
        chat = getIntent().getParcelableExtra(Constants.CHAT);
        if(chat==null){
            tutoring = getIntent().getParcelableExtra(Constants.TUTORING);
            User user = new User(firebaseUser.getDisplayName(),firebaseUser.getEmail(),firebaseUser.getUid());
            chat = new Chat(user,tutoring.getTutor());
            final DatabaseReference db = chatReference.child(chat.getChatKey());
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        db.setValue(chat);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        this.setTitle(chat.getTutor().getFirstName()+" "+chat.getTutor().getLastName());
        chatKey=chat.getChatKey();
        sendButton = findViewById(R.id.send_button);
        input = findViewById(R.id.input);
        databaseReference =  FirebaseDatabase.getInstance().getReference().child("chat_messages");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMessage msg = new ChatMessage(chat.getUser(),input.getText().toString());
                databaseReference.child(chatKey).push().setValue(msg);
                input.setText("");
            }
        });
        displayChatMessages();
    }

    private void displayChatMessages() {
        listOfMessages = findViewById(R.id.list_of_messages);
        chatAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,R.layout.item_message, databaseReference.child(chatKey)) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = v.findViewById(R.id.message_text);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);
                // Set their text
                messageText.setText(model.getText());
                messageUser.setText(model.getAuthor().getName());
                messageTime.setText(DateFormat.format("MM-dd-yyyy (HH:mm)",model.getDate()));
            }
        };
        listOfMessages.setAdapter(chatAdapter);
    }

    @Override
    public void onBackPressed() {
        //moveTaskToBack(true);
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id==R.id.action_chat){
            openChat();
            return true;
        }else if(id == R.id.action_profile) {
            openProfile();
            return true;
        } else if (id == R.id.action_logout){
            logOut();
            return true;
        } else if(id == R.id.action_main){
            openMain();
            return true;
        }else if(id == R.id.action_search){
            openSearch();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openChat(){
        Intent intent = new Intent(ActivityChat.this,ActivityAllChats.class);
        startActivity(intent);
        finish();
    }

    private void openMain(){
        Intent intent = new Intent(ActivityChat.this,ActivityMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ActivityChat.this, ActivitySplash.class);
        startActivity(intent);
        finish();
    }
    private void openProfile(){
        Intent intent = new Intent(ActivityChat.this,ActivityProfile.class);
        startActivity(intent);
        finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivityChat.this,ActivitySearch.class);
        startActivity(intent);
        finish();
    }

}
