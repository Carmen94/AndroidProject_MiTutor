package com.iteso.mitutor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iteso.mitutor.beans.User;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();//firebase

    }


    @Override
    public void onStart() {//firebase
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (currentUser == null || !currentUser.isEmailVerified()) {
                    Intent intent = new Intent(ActivitySplash.this, ActivityLogin.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(ActivitySplash.this, ActivityMain.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1300);


    }
}