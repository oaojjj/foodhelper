package com.example.stdmanagement.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.stdmanagement.Data;
import com.example.stdmanagement.R;

import java.util.Arrays;
import java.util.List;

public class Stone_MenuActivity extends AppCompatActivity {

    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stone_kr_menu);

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
        List<String> listType = Arrays.asList("그릴","그릴","그릴","오븐","오븐","오븐","한식","한식",
                "한식","한식","분식","분식");
        List<String> listName = Arrays.asList("불고기야채철판볶음밥","닭야채철판볶음밥","날치알김치철판볶음밥","포테이토 또띠아 피자",
                "고구마무스 또띠아 피자","김치그라탕","닭갈비컵밥","달걀간장컵밥","제육덮밥","참치야채비빔밥","만두라면","짜계치"
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
                R.drawable.stone_grill_bulgogi_vegetable,
                R.drawable.stone_grill_chick_vegetable,
                R.drawable.stone_grill_nalchibaby,
                R.drawable.stone_oven_potatopizza,
                R.drawable.stone_oven_sweetpotatopizza,
                R.drawable.stone_kimchigratang,
                R.drawable.stone_kr_chickencuprice,
                R.drawable.stone_kr_eggsoycuprice,
                R.drawable.stone_kr_porkrice,
                R.drawable.stone_kr_tuna_vegetable_rice,
                R.drawable.stone_school_chickencupbab,
                R.drawable.stone_school_jjagechi

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