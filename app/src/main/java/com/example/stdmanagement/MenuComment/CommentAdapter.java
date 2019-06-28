package com.example.stdmanagement.MenuComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.stdmanagement.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private ArrayList<CommentItems> items = new ArrayList<>();
    private Context mConext;

    public CommentAdapter(Context context, ArrayList<CommentItems> items) {
        mConext = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View BaseView = View.inflate(mConext, R.layout.item_comment,null);
        CommentViewHolder commentViewHolder = new CommentViewHolder(BaseView);
        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int i) {
        CommentItems item = items.get(i);

        commentViewHolder.stdID.setText(item.getStdID());
        commentViewHolder.comment.setText(item.getComment());
        commentViewHolder.date.setText(item.getDate());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
