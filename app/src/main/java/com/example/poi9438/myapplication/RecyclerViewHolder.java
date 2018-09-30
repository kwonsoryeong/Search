package com.example.poi9438.myapplication;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public ImageView mImage;
    public TextView mName;
    public TextView mScore;
    public ImageButton mZzim;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mName = (TextView) itemView.findViewById(R.id.name);
        mScore = (TextView) itemView.findViewById(R.id.score);
        mZzim = (ImageButton) itemView.findViewById(R.id.zzim_button);
    }
}

