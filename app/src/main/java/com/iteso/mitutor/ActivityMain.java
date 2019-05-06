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

import com.google.firebase.auth.FirebaseAuth;
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
    private ArrayList<Tutoring> mathTutorings,statisticsTutoring,calculusTutorings,cTutorings;
    private ArrayList<Subject> listOfSubjects;
    private RecyclerView.Adapter mathAdapter,statisticsAdapter,calculusAdapter,cAdapter;
    DatabaseReference databaseReference;
    RecyclerView mathRecyclerView,algebraRecyclerView,calculusRecyclerView,cRecyclerView;
    TextView mathMore, statisticsMore,calculusMore,cMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mathTutorings = new ArrayList<>();
        statisticsTutoring = new ArrayList<>();
        calculusTutorings = new ArrayList<>();
        cTutorings = new ArrayList<>();
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
                        statisticsTutoring.add(tutoring);
                    }else if(tutoring.getSubject().getSubjectId().equals("4")){
                        cTutorings.add(tutoring);
                    }else if(tutoring.getSubject().getSubjectId().equals("3")){
                        calculusTutorings.add(tutoring);
                    }
                }
                mathAdapter.notifyDataSetChanged();
                statisticsAdapter.notifyDataSetChanged();
                calculusAdapter.notifyDataSetChanged();
                cAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mathRecyclerView = (RecyclerView) findViewById(R.id.math_recycler_view);
        algebraRecyclerView = (RecyclerView) findViewById(R.id.statistics_recycler_view);
        calculusRecyclerView = (RecyclerView) findViewById(R.id.calculus_recycler_view);
        cRecyclerView = (RecyclerView) findViewById(R.id.c_recycler_view);
        mathMore = findViewById(R.id.math_more);
        statisticsMore = findViewById(R.id.statistics_more);
        calculusMore = findViewById(R.id.calculus_more);
        cMore = findViewById(R.id.c_more);

        mathMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this,ActivityAllSubjects.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

       statisticsMore.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ActivityMain.this,ActivityAllSubjects.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
           }
        });

        calculusMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this,ActivityAllSubjects.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        cMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this,ActivityAllSubjects.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        // Create adapter passing in the sample user data
        mathAdapter = new AdapterTutorings(this,mathTutorings);
        mathRecyclerView.setAdapter(mathAdapter);
        statisticsAdapter = new AdapterTutorings(this,statisticsTutoring);
        algebraRecyclerView.setAdapter(statisticsAdapter);
        calculusAdapter = new AdapterTutorings(this,calculusTutorings);
        calculusRecyclerView.setAdapter(calculusAdapter);
        cAdapter = new AdapterTutorings(this,cTutorings);
        cRecyclerView.setAdapter(cAdapter);
        LinearLayoutManager hMathLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mathRecyclerView.setLayoutManager(hMathLayout);
        algebraRecyclerView.setLayoutManager(horizontalLayoutManager2);
        calculusRecyclerView.setLayoutManager(horizontalLayoutManager3);
        cRecyclerView.setLayoutManager(horizontalLayoutManager4);
    }

    @Override
    public void onBackPressed() {
        finish();
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
            return false;
        } else if (id == R.id.action_logout){
            logOut();
            return false;
        } else if (id == R.id.action_profile){
            openProfile();
            return false;
        } else if(id == R.id.action_search){
            openSearch();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openChat(){
        Intent intent = new Intent(ActivityMain.this,ActivityAllChats.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        //finish();
    }

    private void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ActivityMain.this, ActivitySplash.class);
        startActivity(intent);
        finish();
    }
    private void openProfile(){
        Intent intent = new Intent(ActivityMain.this,ActivityProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        //finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivityMain.this,ActivitySearch.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        //finish();
    }

}

