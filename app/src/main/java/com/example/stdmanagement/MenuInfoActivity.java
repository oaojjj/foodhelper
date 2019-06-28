package com.example.stdmanagement;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.stdmanagement.MenuComment.CommentAdapter;
import com.example.stdmanagement.MenuComment.CommentItems;
import com.example.stdmanagement.Retrofit.RetrofitClient;
import com.example.stdmanagement.Retrofit.RetrofitData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuInfoActivity extends AppCompatActivity {

    TextView tvFoodName, tvPrice, tvInfo;
    RatingBar ratingBar;
    Button btRatingCommit,btCommentCommit;
    EditText etComment;
    AlertDialog dialog;
    String id;
    String cafe_type;
    String kor_name;
    String price;
    String info;
    String avg_rating;
    String user_rating;
    CommentAdapter adapter;
    ArrayList<CommentItems> itemsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);



        final String userid = LoginActivity.userID;
        int i;

        tvFoodName = findViewById(R.id.tv_foodName);
        tvPrice = findViewById(R.id.tv_price);
        tvInfo = findViewById(R.id.tv_infoMenu);
        ratingBar = findViewById(R.id.ratingbar);
        btRatingCommit = findViewById(R.id.bt_ratingCommit);
        btCommentCommit = findViewById(R.id.bt_commit);
        etComment = findViewById(R.id.et_comment);

        final RecyclerView recyclerView = findViewById(R.id.rv_comment);

        Intent intent = getIntent();
        String foodName = intent.getExtras().getString("foodName");

        ArrayList<Map<String,String>> foodData = RetrofitData.getArrayList();
        for(i=0;i<foodData.size();i++){
            if(foodData.get(i).get("kor_name").equals(foodName)){
                id = foodData.get(i).get("id");
                cafe_type = foodData.get(i).get("cafe_type");
                kor_name = foodData.get(i).get("kor_name");
                price = foodData.get(i).get("price");
                info = foodData.get(i).get("info");
                avg_rating = foodData.get(i).get("avg_rating");
                i++;
                break;
            }
        }

        btCommentCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = etComment.getText().toString();

                Call<ResponseBody> call3 = RetrofitClient
                        .getInstance()
                        .getApi()
                        .InsertComment(id,userid,comment);

                call3.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("test99999",response.message());
                        Boolean flag = null;
                        try {
                            flag = Boolean.valueOf(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(flag){
                            AlertDialog.Builder builder = new AlertDialog.Builder(MenuInfoActivity.this);
                            dialog = builder.setMessage("한줄평을 남겼습니다.")
                                    .create();
                            dialog.show();
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(MenuInfoActivity.this);
                            dialog = builder.setMessage("이미 한줄평이 있습니다.")
                                    .create();
                            dialog.show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

        Call<ResponseBody> call2 = RetrofitClient
                .getInstance()
                .getApi()
                .Comment(id);

        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    itemsArrayList = new ArrayList<>();
                    adapter = new CommentAdapter(getApplicationContext(),itemsArrayList);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray jsonArray = jsonObject.getJSONArray("comment");

                    Log.d("what_is_json?",jsonArray.toString());
                    for(int j=0;j<jsonArray.length();j++){
                        JSONObject item = jsonArray.getJSONObject(j);

                        CommentItems items = new CommentItems(item.optString("stdID"),item.optString("comment"),item.optString("time"));
                        itemsArrayList.add(items);
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),1,false));
                    adapter = new CommentAdapter(getApplicationContext(),itemsArrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    Log.d("test",response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
               .RatingInfo(id,userid);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    user_rating = response.body().string();
                    ratingBar.setRating(Float.parseFloat(user_rating));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



        tvFoodName.setText(kor_name);
        tvPrice.setText(price);
        tvInfo.setText(info);




        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, final float rating, boolean fromUser) {
                btRatingCommit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Call<ResponseBody> call = RetrofitClient
                                .getInstance()
                                .getApi()
                                .FoodRating(id,userid, String.valueOf(rating));

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    boolean flag = Boolean.parseBoolean(response.body().string());
                                    Log.d("9999999", String.valueOf(flag));

                                    if (flag){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MenuInfoActivity.this);
                                        dialog = builder.setMessage("적용되었습니다.")
                                                .create();
                                        dialog.show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });


                    }
                });
            }
        });

    }
    @Override
    protected void onStop(){
        super.onStop();
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        } //dismiss 필수!!!!
    }
}
