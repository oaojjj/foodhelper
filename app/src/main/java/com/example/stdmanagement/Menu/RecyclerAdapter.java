package com.example.stdmanagement.Menu;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stdmanagement.MenuInfoActivity;
import com.example.stdmanagement.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();
    private Context context;
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        context = parent.getContext();
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 CommentViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 CommentViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        private TextView tvType;
        private TextView tvAvg;
        private ImageView ivFoodPhoto;
        private TextView tvFoodName;
        private Data data;

        ItemViewHolder(View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_type);
            tvFoodName = itemView.findViewById(R.id.tv_foodName);
            tvAvg = itemView.findViewById(R.id.tv_avg);
            ivFoodPhoto = itemView.findViewById(R.id.iv_foodPhoto);
        }

        void onBind(Data data) {
            this.data = data;
            tvType.setText(data.getType());
            tvFoodName.setText(data.getName());
            tvAvg.setText(data.getContent());
            ivFoodPhoto.setImageResource(data.getResId());


            tvFoodName.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context.getApplicationContext(), MenuInfoActivity.class);
            intent.putExtra("foodName",tvFoodName.getText().toString());
            context.startActivity(intent);
        }
    }
}