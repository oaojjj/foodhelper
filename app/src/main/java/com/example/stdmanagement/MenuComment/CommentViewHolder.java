package com.example.stdmanagement.MenuComment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stdmanagement.R;

public class CommentViewHolder extends RecyclerView.ViewHolder{
    public TextView stdID;
    public TextView comment;
    public TextView date;
    public Button update;

    CommentViewHolder(@NonNull View itemView){
        super(itemView);
        stdID = itemView.findViewById(R.id.tv_stdID);
        comment = itemView.findViewById(R.id.tv_comment);
        date = itemView.findViewById(R.id.tv_date);
        update = itemView.findViewById(R.id.bt_commentUpdate);
    }

}
