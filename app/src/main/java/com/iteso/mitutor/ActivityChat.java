package com.iteso.mitutor;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iteso.mitutor.beans.Chat;
import com.iteso.mitutor.beans.ChatMessage;
import com.iteso.mitutor.tools.Constants;


public class ActivityChat extends AppCompatActivity {
    Chat chat;
    String chatKey;
    FloatingActionButton sendButton;
    ListView listOfMessages;
    EditText input;
    private FirebaseListAdapter<ChatMessage> chatAdapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chat = getIntent().getParcelableExtra(Constants.CHAT);
        this.setTitle(chat.getTutor().getFirstName()+" "+chat.getTutor().getLastName());
        chatKey=chat.getChatKey();
        sendButton = findViewById(R.id.send_button);
        input = findViewById(R.id.input);
        databaseReference =  FirebaseDatabase.getInstance().getReference().child("chats");
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
        if (id == R.id.action_profile) {
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

    private void openMain(){
        Intent intent = new Intent(ActivityChat.this,ActivityMain.class);
        startActivity(intent);
//        finish();
    }

    private void logOut(){
        Intent intent = new Intent(ActivityChat.this,ActivityLogin.class);
        startActivity(intent);
//        finish();
    }
    private void openProfile(){
        Intent intent = new Intent(ActivityChat.this,ActivityProfile.class);
        startActivity(intent);
//        finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivityChat.this,ActivitySearch.class);
        startActivity(intent);
//        finish();
    }

}
