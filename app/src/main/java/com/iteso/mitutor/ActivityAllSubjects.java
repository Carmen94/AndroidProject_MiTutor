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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.mitutor.beans.Subject;
import com.iteso.mitutor.beans.Tutoring;
import com.iteso.mitutor.tools.AdapterAllSubjects;

import java.util.ArrayList;

public class ActivityAllSubjects extends AppCompatActivity {
    private ArrayList<Subject> subjects;
    private RecyclerView.Adapter subjectAdapter;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    boolean init=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_subjects);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subject, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
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
        Intent intent = new Intent(ActivityAllSubjects.this,ActivityAllChats.class);
        startActivity(intent);
        finish();
    }

    private void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ActivityAllSubjects.this, ActivitySplash.class);
        startActivity(intent);
        finish();
    }

    private void openMain(){
        Intent intent = new Intent(ActivityAllSubjects.this,ActivityMain.class);
        startActivity(intent);
        finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivityAllSubjects.this,ActivitySearch.class);
        startActivity(intent);
        finish();
    }
    private void openProfile(){
        Intent intent = new Intent(ActivityAllSubjects.this,ActivityProfile.class);
        startActivity(intent);
        finish();
    }
}
