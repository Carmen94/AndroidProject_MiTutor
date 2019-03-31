package com.iteso.mitutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.iteso.mitutor.beans.Message;
import com.iteso.mitutor.beans.Subject;
import com.iteso.mitutor.tools.AdapterAllChats;
import com.iteso.mitutor.tools.AdapterAllSubjects;

import java.util.ArrayList;

public class ActivityAllChats extends AppCompatActivity {
    private ArrayList<Message> chats;
    private RecyclerView.Adapter messageAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chats);

        recyclerView = findViewById(R.id.all_chats_recycler_view);
        chats = new ArrayList<>();
        Message chat1 = new Message();
        chat1.setAuthor("Manuel Díaz");

        Message chat2 = new Message();
        chat2.setAuthor("Carlos Torres");

        Message chat3 = new Message();
        chat3.setAuthor("Miriam García");

        chats.add(chat1);
        chats.add(chat2);
        chats.add(chat3);
        messageAdapter = new AdapterAllChats(this,chats);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_chat) {
            openChat();
            return true;
        } else if (id == R.id.action_logout){
            logOut();
            return true;
        } else if (id == R.id.action_profile){
            openProfile();
            return true;
        } else if(id == R.id.action_main){
            openMain();
        }else if(id == R.id.action_search){
            openSearch();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openChat(){
        Intent intent = new Intent(ActivityAllChats.this,ActivityAllChats.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void logOut(){
        Intent intent = new Intent(ActivityAllChats.this,ActivityLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void openMain(){
        Intent intent = new Intent(ActivityAllChats.this,ActivityMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivityAllChats.this,ActivitySearch.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    private void openProfile(){
        Intent intent = new Intent(ActivityAllChats.this,ActivityProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
