package com.example.databasehomewoek;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    public TextView x;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        x=itemView.findViewById(R.id.main_text);
    }
}
