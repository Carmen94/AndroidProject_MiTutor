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
import com.iteso.mitutor.tools.AdapterSubjectDetail;
import com.iteso.mitutor.tools.Constants;

import java.util.ArrayList;

public class ActivitySubject extends AppCompatActivity {
    private ArrayList<Tutoring> tutorings;
    ArrayList<Tutoring> allTutorings;
    private Subject subject;
    private RecyclerView.Adapter subjectAdapter;
    RecyclerView subjectRecyclerView;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        tutorings = new ArrayList<>();
        allTutorings = new ArrayList<>();
        subjectRecyclerView = (RecyclerView) findViewById(R.id.subject_category_recycler_view);
        databaseReference =  FirebaseDatabase.getInstance().getReference();
        subject = getIntent().getParcelableExtra(Constants.SUBJECT);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.child("tutorings").getChildren()){
                    Tutoring tutoring = snapshot.getValue(Tutoring.class);
                    allTutorings.add(tutoring);
                }
                for (Tutoring t: allTutorings) {
                    if (t.getSubject().getSubjectId().equals(subject.getSubjectId())) {
                        tutorings.add(t);
                    }
                }
                subjectAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        subjectAdapter = new AdapterSubjectDetail(this,tutorings);
        subjectRecyclerView.setAdapter(subjectAdapter);
        subjectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        Intent intent = new Intent(ActivitySubject.this,ActivityAllChats.class);
        startActivity(intent);
        finish();
    }

    private void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ActivitySubject.this, ActivitySplash.class);
        startActivity(intent);
        finish();
    }
    private void openMain(){
        Intent intent = new Intent(ActivitySubject.this,ActivityMain.class);
        startActivity(intent);
        finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivitySubject.this,ActivitySearch.class);
        startActivity(intent);
        finish();
    }
    private void openProfile(){
        Intent intent = new Intent(ActivitySubject.this,ActivityProfile.class);
        startActivity(intent);
        finish();
    }
}
