package com.example.fcis_ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class show_category extends AppCompatActivity {
    ImageView shirtcat , accesscat , shoecat ;
    Database db ;
    Button logout ;
SharedPreferences shared ;
SharedPreferences.Editor edit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category);
        shirtcat = findViewById(R.id.tshirt_cat);
        accesscat = findViewById(R.id.access_cat);
        shoecat = findViewById(R.id.shoe_cat);
        logout=findViewById(R.id.logout_btn);
        shared = getSharedPreferences("info",MODE_PRIVATE);
        edit = shared.edit();
        shirtcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(show_category.this,product1.class);
                startActivity(i);
            }
        });

        shoecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(show_category.this,product2.class);
                startActivity(i);
            }
        });
        accesscat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(show_category.this,product3.class);
                startActivity(i);
            }
        });
   logout.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           edit.clear();
           edit.apply();
           Intent i = new Intent (show_category.this,MainActivity.class);
           startActivity(i);
       }
   });
    }
}