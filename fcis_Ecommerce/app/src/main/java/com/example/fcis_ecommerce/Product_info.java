package com.example.fcis_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Product_info extends AppCompatActivity {
ImageView img_info;
TextView title_info;
String data1 ;
int img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        img_info = findViewById(R.id.img_info);
        title_info = findViewById(R.id.Title_info);
        getdata();
        setdata();
    }
    private void getdata(){
        if (getIntent().hasExtra("img")&& getIntent().hasExtra("data1")) ;
        data1 = getIntent().getStringExtra("data1");
        img = getIntent().getIntExtra("img",1);
    }
    private void setdata(){
    title_info.setText(data1);
    img_info.setImageResource(img);
    }
}