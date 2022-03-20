package com.example.fcis_ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class product1 extends AppCompatActivity{
String s1 [];
Database db ;
Button searchOnItems;
RecyclerView recycleproduct ;
ViewProduct viewww ;
productAdapter pro ;

int images [] = {R.drawable.pinkshirt,R.drawable.purple,R.drawable.blackshirt,R.drawable.yellowshirt,R.drawable.greenshirt};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product1);
        db = new Database(this);
        recycleproduct = findViewById(R.id.shirt_recyle);
   s1 = getResources().getStringArray(R.array.products);
        db.insertProduct("purple shirt",100,10,1);
        db.insertProduct("black shirt",150,7,1);
        db.insertProduct("pink shirt",135.5,9,1);
        db.insertProduct("yellow shirt",200,20,1);
        db.insertProduct("green shirt",400,5,1);
         productAdapter adapter = new productAdapter( s1 ,  this);
        recycleproduct.setAdapter(adapter);
   recycleproduct.setLayoutManager(new LinearLayoutManager(this));
    searchOnItems = findViewById(R.id.search_PRODUCT);
    searchOnItems.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(product1.this,Home.class);
            startActivity(i);
        }
    });
    }
    /*public void onItemClick(int position) {
        Intent i = new Intent(product1.this,item1.class);
        i.putExtra("data1",pro.data1[position]);
      //  i.putExtra("img",pro.images[position]);
        startActivity(i);
    }*/
}