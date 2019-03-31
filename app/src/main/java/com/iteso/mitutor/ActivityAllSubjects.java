package com.iteso.mitutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.iteso.mitutor.beans.Subject;
import com.iteso.mitutor.tools.AdapterAllSubjects;
import com.iteso.mitutor.tools.AdapterSubject;
import com.iteso.mitutor.tools.AdapterSubjectDetail;

import java.util.ArrayList;

public class ActivityAllSubjects extends AppCompatActivity {
    private ArrayList<Subject> subjects;
    private RecyclerView.Adapter subjectAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_subjects);
        recyclerView = findViewById(R.id.all_subjects_recycler_view);
        subjects = new ArrayList<>();

        Subject s1 = new Subject();
        s1.setSubjectName("Math");
        s1.setId(0);
        s1.setSubjectCode("2D");

        Subject s2 = new Subject();
        s2.setSubjectName("Calculo Integral");
        s2.setId(0);
        s2.setSubjectCode("2D");

        Subject s3 = new Subject();
        s3.setSubjectName("Calculo Diferencial");
        s3.setId(0);
        s3.setSubjectCode("2D");

        Subject s4 = new Subject();
        s4.setSubjectName("Calculo Multivariable");
        s4.setId(0);
        s4.setSubjectCode("2D");

        Subject s5 = new Subject();
        s5.setSubjectName("Mecanica");
        s5.setId(0);
        s5.setSubjectCode("2D");

        Subject s6 = new Subject();
        s6.setSubjectName("Estatica y Dinamica");
        s6.setId(0);
        s6.setSubjectCode("2D");

        Subject s7 = new Subject();
        s7.setSubjectName("Logica Matematica");
        s7.setId(0);
        s7.setSubjectCode("2D");

        subjects.add(s1); subjects.add(s2); subjects.add(s3); subjects.add(s4); subjects.add(s5); subjects.add(s6); subjects.add(s7);
        subjectAdapter = new AdapterAllSubjects(this,subjects);
        recyclerView.setAdapter(subjectAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        Intent intent = new Intent(ActivityAllSubjects.this,ActivityChat.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void logOut(){
        Intent intent = new Intent(ActivityAllSubjects.this,ActivityLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void openMain(){
        Intent intent = new Intent(ActivityAllSubjects.this,ActivityMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivityAllSubjects.this,ActivitySearch.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    private void openProfile(){
        Intent intent = new Intent(ActivityAllSubjects.this,ActivityProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
