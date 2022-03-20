package com.example.fcis_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class item1 extends AppCompatActivity {

    ImageView item_img;
    TextView item_name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item1);
        item_img =findViewById(R.id.image_item);
        item_name = findViewById(R.id.name_item);
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            String value = extra.getString("data1");
            int img = extra.getInt("img");
            item_img.setImageResource(img);
            item_name.setText(value);
        }
    }
}