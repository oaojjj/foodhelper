package com.example.stdmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


public class MenuInfoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);


        ImageView imageView = findViewById(R.id.iv_foodPhoto);
        imageView.setImageResource(R.drawable.old_china_bbok_jja);


    }
}
