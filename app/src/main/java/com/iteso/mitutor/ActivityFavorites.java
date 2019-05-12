package com.iteso.mitutor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.iteso.mitutor.beans.Subject;
import com.iteso.mitutor.tools.AdapterFavorites;
import com.iteso.mitutor.tools.AdapterSearch;

import java.util.ArrayList;

public class ActivityFavorites extends AppCompatActivity {

    RecyclerView recyclerView;
    private AdapterFavorites adapter;
    private Context c;
    ArrayList<Subject> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        fillList();
        setUpRecyclerView();

    }

    private void fillList(){
        arrayList = new ArrayList<>();
        arrayList.add(new Subject("1", "Statistics"));
        arrayList.add(new Subject("2", "Mathamethics"));
        arrayList.add(new Subject("3", "Differential Calculus"));
        arrayList.add(new Subject("4", "C Programming"));
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.favorites_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new AdapterFavorites(this,arrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            returnProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void returnProfile(){
        Intent intent = new Intent(ActivityFavorites.this,ActivityProfile.class);
        startActivity(intent);
        finish();
    }

}
