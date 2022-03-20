package com.example.fcis_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    Cursor match ;
    ArrayAdapter<String> productadapt ;
    Database db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new Database(this);
        EditText search  = (EditText)findViewById(R.id.search_txt);
        ImageButton search_btn = (ImageButton)findViewById(R.id.search_btn);
        final ListView list_search = (ListView)findViewById(R.id.list_search);
        productadapt = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1);
        list_search.setAdapter(productadapt);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String proname = search.getText().toString();
                match = db.getproductname(proname);
                productadapt.clear();
                if (match != null){
                    while ( (!match.isAfterLast())){
                        productadapt.add(match.getString(1));
                        match.moveToNext();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"no matched product try again .. ",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}