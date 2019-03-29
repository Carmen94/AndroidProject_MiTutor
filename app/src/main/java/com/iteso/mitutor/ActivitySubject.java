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
import com.iteso.mitutor.tools.AdapterSubjectDetail;

import java.util.ArrayList;

public class ActivitySubject extends AppCompatActivity {
    private ArrayList<Subject> listOfSubjects;
    private RecyclerView.Adapter subjectAdapter;
    RecyclerView subjectRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        listOfSubjects = new ArrayList<>();
        subjectRecyclerView = (RecyclerView) findViewById(R.id.subject_category_recycler_view);

        Subject subject = new Subject();
        subject.setSubjectName("Math");
        subject.setTutorName("Patrick");
        subject.setId(0);
        subject.setSubjectCode("2D");
        subject.setSubjectImageUrl("URL");
        subject.setTuitionLocation("ITESO");
        subject.setTutorPhone("31217777");
        subject.setScore("10/10");
        subject.setTutorDescription("Students of engineering");

        Subject subject2 = new Subject();
        subject2.setSubjectName("Math II");
        subject2.setTutorName("Patrick");
        subject2.setId(0);
        subject2.setSubjectCode("2D");
        subject2.setSubjectImageUrl("URL");
        subject2.setTuitionLocation("ITESO");
        subject2.setTutorPhone("00000000");
        subject2.setScore("8/10");
        subject2.setTutorDescription("Students of engineering");

        listOfSubjects.add(subject);
        listOfSubjects.add(subject2);
        subjectAdapter = new AdapterSubjectDetail(this,listOfSubjects);
        subjectRecyclerView.setAdapter(subjectAdapter);
        subjectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
            //Open Profile
        } else if(id == R.id.action_main){
            openMain();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openChat(){
        Intent intent = new Intent(ActivitySubject.this,ActivityChat.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void logOut(){

    }

    private void openMain(){
        Intent intent = new Intent(ActivitySubject.this,ActivityMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
