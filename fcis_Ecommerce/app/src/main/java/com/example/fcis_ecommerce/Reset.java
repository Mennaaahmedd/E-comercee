package com.example.fcis_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reset extends AppCompatActivity {
    //TextView username ;
    EditText password , username ;
    Button get_pass;
    Database db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        username = (EditText) findViewById(R.id.User_reset);
        password = (EditText)findViewById(R.id.password_reset);
        get_pass = (Button)findViewById(R.id.Save_btn);
        db = new Database(this);
        Intent intent = getIntent();
        get_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                boolean checkuser = db.checkUserName(user);
                if (checkuser == true) {
                    String return_pass = db.resetPassword(user);
                    password.setText(return_pass);
                    Toast.makeText(getApplicationContext(), "password returned .. ", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "user doesn't exsist .. ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}