package com.iteso.mitutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            openProfile();
            return true;
        } else if (id == R.id.action_logout){
            logOut();
            return true;
        } else if(id == R.id.action_main){
            openMain();
            return true;
        }else if(id == R.id.action_search){
            openSearch();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openMain(){
        Intent intent = new Intent(ActivityChat.this,ActivityMain.class);
        startActivity(intent);
//        finish();
    }

    private void logOut(){
        Intent intent = new Intent(ActivityChat.this,ActivityLogin.class);
        startActivity(intent);
//        finish();
    }
    private void openProfile(){
        Intent intent = new Intent(ActivityChat.this,ActivityProfile.class);
        startActivity(intent);
//        finish();
    }
    private void openSearch(){
        Intent intent = new Intent(ActivityChat.this,ActivitySearch.class);
        startActivity(intent);
//        finish();
    }

}
