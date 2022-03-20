package com.example.fcis_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class product_details extends AppCompatActivity  {
ImageView imgg ;
TextView Price_Detail, Name_detail ;
String name , price , quan , pic;
ImageButton addcart_detail;
product1 p1 ;
productAdapter adapt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        imgg= findViewById(R.id.imgproduct_detail);
        Price_Detail = findViewById(R.id.productPrice_detail);
        Name_detail = findViewById(R.id.productName_detail);
        String name=getIntent().getExtras().getString("h2");
        Name_detail.setText(name);
        int image=getIntent().getExtras().getInt("h1");
        imgg.setImageResource(image);

     //imgg.setImageResource(p1.images[2]);
    }


}