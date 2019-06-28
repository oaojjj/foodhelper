package com.example.stdmanagement.MenuComment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.stdmanagement.R;

public class CommentViewHolder extends RecyclerView.ViewHolder{
    public TextView stdID;
    public TextView comment;

    CommentViewHolder(@NonNull View itemView){
        super(itemView);
        stdID = itemView.findViewById(R.id.tv_stdID);
        comment = itemView.findViewById(R.id.tv_comment);
    }

}
