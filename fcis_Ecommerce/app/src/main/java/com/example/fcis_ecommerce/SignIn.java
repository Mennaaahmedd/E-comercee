package com.example.fcis_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
TextView create , forgetpass;
Button SIGNINN ;
EditText Name_log ;
EditText Password_log;
CheckBox Rememberme;
SharedPreferences shared;
SharedPreferences.Editor edit ;
Database db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        create = findViewById(R.id.create_log);
        forgetpass = findViewById(R.id.foget_);
        SIGNINN = findViewById(R.id.SIGNIN_);
        Name_log = findViewById(R.id.name_log);
        db = new Database(this);
        Password_log = findViewById(R.id.pass_log);
        Rememberme = findViewById(R.id.remember_box);

        shared = getSharedPreferences("info",MODE_PRIVATE);
        edit = shared.edit();
        SIGNINN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name =Name_log.getText().toString();
                String user_pass=Password_log.getText().toString();
                if(user_name.equals("")||user_pass.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter the fields",Toast.LENGTH_LONG).show();
                }
                else {
                    boolean check = db.checkUserName_Pass(user_name,user_pass);
                    if (check == true) {
                        if(Rememberme.isChecked()){
                     edit.putString("username" , user_name);
                     edit.putString("password",user_pass);
                     edit.apply();
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(SignIn.this,show_category.class);
                        startActivity(intent);
                        }
                        else {
                            edit.clear();
                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(SignIn.this,show_category.class);
                            startActivity(intent);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
                }
                }
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignIn.this,Register.class);
                startActivity(i);
            }
        });
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignIn.this,Reset.class);
                startActivity(i);
            }
        });
        getdata();

    }
    public  void getdata()
    {
        shared = getSharedPreferences("info",MODE_PRIVATE);
        String name =shared.getString("username","Not found");
        Name_log.setText(name);

        String pass =shared.getString("password","Not found");
        Password_log.setText(pass);

    }
}