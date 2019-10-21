package com.example.alekseyignatenko.rss_reader.NetWork;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.alekseyignatenko.rss_reader.model.RssObject;

public class MyCallBack extends DiffUtil.ItemCallback<RssObject> {



    @Override
    public boolean areItemsTheSame(@NonNull RssObject oldItem, @NonNull RssObject newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull RssObject oldItem, @NonNull RssObject newItem) {
        return false;
    }
}
