package com.iteso.mitutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.iteso.mitutor.beans.Subject;
import com.iteso.mitutor.tools.AdapterSubject;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {
    private ArrayList<Subject> mathSubjects;
    private ArrayList<Subject> algebraSubjects;
    private RecyclerView.Adapter subjectAdapter;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView mathRecyclerView;
    RecyclerView algebraRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mathSubjects = new ArrayList<>();
        algebraSubjects = new ArrayList<>();
        mathRecyclerView = (RecyclerView) findViewById(R.id.math_recycler_view);
        algebraRecyclerView = (RecyclerView) findViewById(R.id.english_recycler_view);
        // Initialize contacts
        Subject subject = new Subject();
        subject.setSubjectName("Math");
        subject.setTutorName("Patrick");
        subject.setId(0);
        subject.setSubjectCode("2D");
        subject.setSubjectImageUrl("URL");
        subject.setTuitionLocation("ITESO");

        Subject subject2 = new Subject();
        subject2.setSubjectName("Math II");
        subject2.setTutorName("Patrick");
        subject2.setId(0);
        subject2.setSubjectCode("2D");
        subject2.setSubjectImageUrl("URL");
        subject2.setTuitionLocation("ITESO");
        mathSubjects.add(subject);
        mathSubjects.add(subject2);
        algebraSubjects.add(subject);
        algebraSubjects.add(subject2);
        // Create adapter passing in the sample user data
        subjectAdapter = new AdapterSubject(this,mathSubjects);
        mathRecyclerView.setAdapter(subjectAdapter);
        RecyclerView.Adapter subjectAdapter2 = new AdapterSubject(this,algebraSubjects);
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
            //Open Profile
        }
        return super.onOptionsItemSelected(item);
    }

    private void openChat(){
        Intent intent = new Intent(ActivityMain.this,ActivityChat.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void logOut(){

    }

}

