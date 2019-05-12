package com.iteso.mitutor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.mitutor.beans.Subject;
import com.iteso.mitutor.tools.AdapterAllSubjects;

import java.util.ArrayList;

public class ActivityProfile extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    TextView username;
    TextView mail;
    ImageView imageView;
    private ArrayList<Subject> subjects;
    private RecyclerView.Adapter subjectAdapter;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    boolean init=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        username = findViewById(R.id.user_name);
        mail = findViewById(R.id.user_mail);
        imageView = findViewById(R.id.imageView);

        username.setText(user.getDisplayName());
        mail.setText(user.getEmail());
        if(user.getPhotoUrl() != null){
            Glide.with(this).load(user.getPhotoUrl().toString()).into(imageView);
        }

        recyclerView = findViewById(R.id.all_subjects_recycler_view);
        subjects = new ArrayList<>();
        databaseReference =  FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!init){
                    for(DataSnapshot snapshot : dataSnapshot.child("subjects").getChildren()){
                        Subject subject = snapshot.getValue(Subject.class);
                        subjects.add(subject);
                    }
                    subjectAdapter.notifyDataSetChanged();
                    init=true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});
        subjectAdapter = new AdapterAllSubjects(this,subjects);
        recyclerView.setAdapter(subjectAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_chat) {
            openChat();
            return true;
        } else if (id == R.id.action_logout){
            logOut();
            return true;
        } else if (id == R.id.action_search){
            openSearch();
            return true;
        } else if(id == R.id.action_main){
            openMain();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openChat(){
        Intent intent = new Intent(ActivityProfile.this,ActivityAllChats.class);
        startActivity(intent);
        finish();
    }

    private void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ActivityProfile.this, ActivitySplash.class);
        startActivity(intent);
        finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivityProfile.this,ActivitySearch.class);
        startActivity(intent);
        finish();
    }
    private void openMain(){
        Intent intent = new Intent(ActivityProfile.this,ActivityMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
