package com.iteso.mitutor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.mitutor.beans.Chat;
import com.iteso.mitutor.beans.ChatMessage;
import com.iteso.mitutor.beans.Subject;
import com.iteso.mitutor.beans.Tutor;
import com.iteso.mitutor.beans.User;
import com.iteso.mitutor.tools.AdapterAllChats;

import java.util.ArrayList;

public class ActivityAllChats extends AppCompatActivity {
    private ArrayList<Chat> chats;
    private RecyclerView.Adapter chatsAdapter;
    RecyclerView recyclerView;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    boolean init=false;
    DatabaseReference databaseReference;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        setContentView(R.layout.activity_all_chats);
        recyclerView = findViewById(R.id.all_chats_recycler_view);
        chats = new ArrayList<>();
        user = new User(firebaseUser.getDisplayName(),firebaseUser.getEmail(),firebaseUser.getUid());
        databaseReference =  FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!init){
                    for(DataSnapshot snapshot : dataSnapshot.child("chats").getChildren()){
                        Chat chat = snapshot.getValue(Chat.class);
                        if(chat!=null){
                            String token[] = chat.getChatKey().split("_");
                            if(token[0].equals(user.getUserId())){
                                chats.add(chat);
                            }
                        }
                    }
                    init=true;
                }
                chatsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});
        chatsAdapter = new AdapterAllChats(this,chats);
        recyclerView.setAdapter(chatsAdapter);
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
        if (id == R.id.action_logout){
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

    private void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ActivityAllChats.this, ActivitySplash.class);
        startActivity(intent);
        finish();
    }

    private void openMain(){
        Intent intent = new Intent(ActivityAllChats.this,ActivityMain.class);
        startActivity(intent);
        finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivityAllChats.this,ActivitySearch.class);
        startActivity(intent);
        finish();
    }
    private void openProfile(){
        Intent intent = new Intent(ActivityAllChats.this,ActivityProfile.class);
        startActivity(intent);
        finish();
    }
}
