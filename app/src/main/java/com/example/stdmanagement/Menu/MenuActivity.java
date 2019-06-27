package com.example.stdmanagement.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.example.stdmanagement.R;
import com.example.stdmanagement.Retrofit.RetrofitClient;
import com.example.stdmanagement.Retrofit.RetrofitData;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    TextView cafeType;
    private RecyclerAdapter adapter;
    String sType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cafeType = findViewById(R.id.tv_cafeType);
        Intent intent = getIntent();

        sType=intent.getExtras().getString("cafe_type");
        cafeType.setText(sType);

        init();
        getData();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        // 임의의 데이터입니다.

        final ArrayList<Map<String,String>> mapArrayList = new ArrayList<>();
        Map map= new HashMap();

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .FoodData(map);


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray jsonArray = jsonObject.getJSONArray("foodData");

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject items = jsonArray.getJSONObject(i);
                        Map map= new HashMap();

                        map.put("id",items.optString("id"));
                        map.put("cafe_type",items.optString("cafe_type"));
                        map.put("kor_name",items.optString("kor_name"));
                        map.put("eng_name",items.optString("eng_name"));
                        map.put("type",items.optString("type"));
                        map.put("price",items.optString("price"));
                        map.put("info",items.optString("info"));
                        map.put("avg_raing",items.optString("avg_raing"));

                        mapArrayList.add(map);
                        Log.d("test1111111",mapArrayList.get(i).toString());
                    }

                    RetrofitData retrofitData = new RetrofitData();
                    retrofitData.setArrayList(mapArrayList);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < mapArrayList.size(); i++) {
                    // 각 List의 값들을 data 객체에 set 해줍니다.
                    if(sType.substring(0,2).equals(mapArrayList.get(i).get("cafe_type"))){
                        Data data = new Data();
                        data.setType(mapArrayList.get(i).get("type"));
                        data.setName(mapArrayList.get(i).get("kor_name"));
                        data.setContent(mapArrayList.get(i).get("avg_rating"));
                        data.setResId(R.drawable.foodphoto);
                        // 각 값이 들어간 data를 adapter에 추가합니다.
                        adapter.addItem(data);
                    }
                }
                // adapter의 값이 변경되었다는 것을 알려줍니다.
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MenuActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        /*List<String> listType = Arrays.asList("정식","양식","양식","양식","일품","일품","일품","일품",
                "스낵","스낵","스낵","스낵");
        List<String> listName = Arrays.asList("다래락 특정식","치즈 돈까스","치킨 마요","돈까스 마요",
                "직화불고기 부대찌개","치즈알밥","직화닭볶음탕","돈까스 김치나베","쫄만두","김치버터라면","라볶이","우엉김밥"
                );
        List<String> listContent = Arrays.asList(
                "4.5",
                "4.0",
                "4.0",
                "4.0",
                "4.0",
                "4.0",
                "4.0",
                "4.0",
                "4.0",
                "4.0",
                "4.0",
                "4.0"

        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.new_regul_1,
                R.drawable.new_america_cheezdongas,
                R.drawable.new_america_chickenmayo,
                R.drawable.new_america_dongasmayo,
                R.drawable.new_ill_boodae_jjigae,
                R.drawable.new_ill_cheezeggbab,
                R.drawable.new_ill_chickenbokkum,
                R.drawable.new_ill_dongas_kimchinabe,
                R.drawable.new_snack_jjolmandoo,
                R.drawable.new_snack_kimchi_butter_ramen,
                R.drawable.new_snack_rabokkgi,
                R.drawable.new_snack_wooung_gimbab

        );*/

    }
}