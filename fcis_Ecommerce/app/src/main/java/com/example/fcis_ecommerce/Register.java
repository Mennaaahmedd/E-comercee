package com.example.fcis_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    EditText username,mail,pass,job , gender , day , month , year , datecelnd;
    TextView account;
    Button reg;
    Database db;
    DatePickerDialog.OnDateSetListener setListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.name_log);
        mail=findViewById(R.id.email_txt);
        pass=findViewById(R.id.pass_log);
        day = findViewById(R.id.day_txt);
        month = findViewById(R.id.month_txt);
        year = findViewById(R.id.year_txt);
        gender=findViewById(R.id.gender_txt);
        job=findViewById(R.id.job);
        datecelnd = findViewById(R.id.Date_celend);
        Calendar calender = Calendar.getInstance();
        final int YEAR = calender.get(calender.YEAR);
        final int MONTHH = calender.get(calender.MONTH);
        final int DAY = calender.get(calender.DAY_OF_MONTH);

        account = findViewById(R.id.account_have_btn);
        reg=findViewById(R.id.SIGNUP_REGISTER);

        db=new Database(this);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String user_mail=mail.getText().toString();
                String password=pass.getText().toString();
                datecelnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog dataa = new DatePickerDialog(Register.this, android.R.style.Theme_Holo_Dialog_MinWidth,new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                            m = m+1 ;
                                 String birthdate = y + "/" + m +"/"+ d;
                                 datecelnd.setText(birthdate);
                            }
                        },YEAR,MONTHH,DAY);
                        dataa.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
                        dataa.show();
                    }
                });

                //String birthdate = day.getText().toString() + "/" + month.getText().toString() +"/"+ year.getText().toString();
                String Gender=gender.getText().toString();
                String birthdate = datecelnd.getText().toString();
                String Job=job.getText().toString();
                if(user.equals("")||user_mail.equals("")||password.equals("")||birthdate.equals("")||Gender.equals("")||Job.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter the fields",Toast.LENGTH_LONG).show();
                }
                else {
                    if(db.checkUserName(user)==false) {
                        db.new_customer(user,user_mail, password, Gender, birthdate, Job);
                        Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_LONG).show();
                        db.insertCategory("tshirts");
                        db.insertCategory("shoes");
                        db.insertCategory("accessories");
                        Intent intent=new Intent(Register.this,SignIn.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "userName is exist", Toast.LENGTH_LONG).show();

                }

            }
        });
account.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(Register.this,SignIn.class);
        startActivity(i);
    }
});
    }
}