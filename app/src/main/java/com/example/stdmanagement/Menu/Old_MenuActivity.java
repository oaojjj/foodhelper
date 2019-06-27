package com.example.stdmanagement.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.stdmanagement.Data;
import com.example.stdmanagement.R;

import java.util.Arrays;
import java.util.List;

public class Old_MenuActivity extends AppCompatActivity {

    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_kr_menu);

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
        List<String> listType = Arrays.asList("한식","한식","한식","한식","중식","중식","중식","중식",
        "퓨전","퓨전","퓨전","퓨전","분식","분식");
        List<String> listName = Arrays.asList("고추장 불고기 비빔밥","닭갈비 백반","돼지김치찌개","삼겹수육",
                "볶짜면","동파육","깐볶밥","야끼밥","치킨마요덮밥","데리야끼치킨볶음밥","등심돈까스","오리불고기 덮밥",
                "참치김밥","해장라면");
        List<String> listTitle = Arrays.asList("★","★","★","★","★","★","★","★","★","★","★","★"
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
                "4.0",
                "4.0",
                "4.0"

        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.old_korean_gochujang_bulgoggi_mixrice,
                R.drawable.old_korean_chicken_galbi_rice,
                R.drawable.old_korean_kimchizzigae,
                R.drawable.old_korean_samggyup_suyuk,
                R.drawable.old_china_bbok_jja,
                R.drawable.old_china_dong_pa_yuk,
                R.drawable.old_china_ggan_bbok,
                R.drawable.old_china_yakkirice,
                R.drawable.old_fusion_chiken_mayo_rice,
                R.drawable.old_fusion_daeriyaki_chiken_bbok,
                R.drawable.old_fusion_dongas,
                R.drawable.old_fusion_duck_rice,
                R.drawable.old_school_tuna,
                R.drawable.old_school_ramen




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