package com.example.sgplayer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    ImageView iv;
    CardView cv;
    TextView tv;


    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        tv=itemView.findViewById(R.id.item_tv);
        cv=itemView.findViewById(R.id.item_card);
        iv=itemView.findViewById(R.id.item_iv);
    }
}
