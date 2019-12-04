package com.example.alekseyignatenko.rss_reader.NetWork;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.alekseyignatenko.rss_reader.model.Article;


public class MyCallBack extends DiffUtil.ItemCallback<Article>{


    @Override
    public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.equals(newItem);
    }
}
