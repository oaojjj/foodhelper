package com.example.stdmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.stdmanagement.Retrofit.RetrofitData;

import java.util.ArrayList;
import java.util.Map;


public class MenuInfoActivity extends AppCompatActivity {

    TextView tvFoodName, tvPrice, tvInfo;
    RatingBar rbAvg;
    Button btRatingCommit,btCommentCommit;
    EditText etComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);

        int i;

        tvFoodName = findViewById(R.id.tv_foodName);
        tvPrice = findViewById(R.id.tv_price);
        tvInfo = findViewById(R.id.tv_infoMenu);
        rbAvg = findViewById(R.id.ratingbar);
        btRatingCommit = findViewById(R.id.bt_ratingCommit);
        btCommentCommit = findViewById(R.id.bt_commit);
        etComment = findViewById(R.id.et_comment);

        Intent intent = getIntent();
        String foodName = intent.getExtras().getString("foodName");

        ArrayList<Map<String,String>> foodData = RetrofitData.getArrayList();
        for(i=0;i<foodData.size();i++){
            Log.d("test55555555",foodData.get(i).get("kor_name"));
            if(foodData.get(i).get("kor_name").equals(foodName))
                break;
        }


        tvFoodName.setText(foodData.get(i).get("kor_name"));
        tvPrice.setText(foodData.get(i).get("price"));
        tvInfo.setText(foodData.get(i).get("info"));





    }
}
