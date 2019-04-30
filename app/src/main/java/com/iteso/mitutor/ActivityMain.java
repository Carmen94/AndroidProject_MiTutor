package com.iteso.mitutor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.mitutor.beans.Subject;
import com.iteso.mitutor.beans.Tutoring;
import com.iteso.mitutor.tools.AdapterTutorings;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {
    private ArrayList<Tutoring> mathTutorings;
    private ArrayList<Tutoring> algebraTutorings;
    private ArrayList<Subject> listOfSubjects;
    private RecyclerView.Adapter subjectAdapter;
    DatabaseReference databaseReference;
    RecyclerView mathRecyclerView;
    RecyclerView algebraRecyclerView;
    TextView mathMore, statisticsMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mathTutorings = new ArrayList<>();
        algebraTutorings = new ArrayList<>();
        listOfSubjects = new ArrayList<>();
        databaseReference =  FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.child("subjects").getChildren()){
                    Subject subject = snapshot.getValue(Subject.class);
                    listOfSubjects.add(subject);
                }
                for(DataSnapshot snapshot : dataSnapshot.child("tutorings").getChildren()){
                    Tutoring tutoring = snapshot.getValue(Tutoring.class);
                    if(tutoring.getSubject().getSubjectId().equals("2")){
                        mathTutorings.add(tutoring);
                    }else if(tutoring.getSubject().getSubjectId().equals("1")){
                        algebraTutorings.add(tutoring);
                    }
                }
                subjectAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mathRecyclerView = (RecyclerView) findViewById(R.id.math_recycler_view);
        algebraRecyclerView = (RecyclerView) findViewById(R.id.statistics_recycler_view);
        mathMore = findViewById(R.id.math_more);
        statisticsMore = findViewById(R.id.statistics_more);

        mathMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this,ActivityAllSubjects.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

       statisticsMore.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ActivityMain.this,ActivityAllSubjects.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
               finish();
           }
        });
        // Create adapter passing in the sample user data
        subjectAdapter = new AdapterTutorings(this,mathTutorings);
        mathRecyclerView.setAdapter(subjectAdapter);
        RecyclerView.Adapter subjectAdapter2 = new AdapterTutorings(this,algebraTutorings);
        algebraRecyclerView.setAdapter(subjectAdapter2);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mathRecyclerView.setLayoutManager(horizontalLayoutManager);
        algebraRecyclerView.setLayoutManager(horizontalLayoutManager2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        } else if(id == R.id.action_search){
            openSearch();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openChat(){
        Intent intent = new Intent(ActivityMain.this,ActivityAllChats.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void logOut(){
        Intent intent = new Intent(ActivityMain.this,ActivityLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    private void openProfile(){
        Intent intent = new Intent(ActivityMain.this,ActivityProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivityMain.this,ActivitySearch.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}

