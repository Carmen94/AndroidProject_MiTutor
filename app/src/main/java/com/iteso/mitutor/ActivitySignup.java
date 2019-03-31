package com.iteso.mitutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivitySignup extends AppCompatActivity {

    EditText name, lastname, bd, email, password;
    Spinner spinner;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.signup_name);
        lastname = findViewById(R.id.signup_lastname);
        bd = findViewById(R.id.signup_birthday);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        spinner = findViewById(R.id.spinner);
        signup = findViewById(R.id.activity_save);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.scholarship, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        name.getText().toString() + " " +
                                lastname.getText().toString() + " " +
                                bd.getText().toString() + " " +
                                email.getText().toString() + " " +
                                spinner.getSelectedItem().toString() + " " +
                                password.getText().toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ActivitySignup.this ,ActivityMain.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
