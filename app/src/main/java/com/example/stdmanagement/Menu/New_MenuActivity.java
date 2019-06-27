package com.example.stdmanagement.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.stdmanagement.Data;
import com.example.stdmanagement.R;

import java.util.Arrays;
import java.util.List;

public class New_MenuActivity extends AppCompatActivity {

    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_kr_menu);

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
        List<String> listType = Arrays.asList("정식","양식","양식","양식","일품","일품","일품","일품",
                "스낵","스낵","스낵","스낵");
        List<String> listName = Arrays.asList("다래락 특정식","치즈 돈까스","치킨 마요","돈까스 마요",
                "직화불고기 부대찌개","치즈알밥","직화닭볶음탕","돈까스 김치나베","쫄만두","김치버터라면","라볶이","우엉김밥"
                );
        List<String> listTitle = Arrays.asList("★","★","★","★","★","★","★","★","★","★"
                ,"★","★");
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

        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setType(listType.get(i));
            data.setName(listName.get(i));
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}