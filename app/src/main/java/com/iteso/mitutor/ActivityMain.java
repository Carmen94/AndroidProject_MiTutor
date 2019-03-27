package com.iteso.mitutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.iteso.mitutor.beans.Subject;
import com.iteso.mitutor.tools.SubjectAdapter;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {
    private ArrayList<Subject> mDataset;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView mathRecyclerView;
    RecyclerView algebraRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataset= new ArrayList<>();
        mathRecyclerView = findViewById(R.id.math_recycle_view);
//        algebraRecyclerView = findViewById(R.id.algebra_recycle_view);
        mAdapter = new SubjectAdapter(mDataset);
        mathRecyclerView.setAdapter(mAdapter);
        algebraRecyclerView.setAdapter(mAdapter);


    }
}

