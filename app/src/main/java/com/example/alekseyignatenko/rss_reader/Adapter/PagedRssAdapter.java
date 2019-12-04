package com.example.alekseyignatenko.rss_reader.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.alekseyignatenko.rss_reader.R;
import com.example.alekseyignatenko.rss_reader.model.Article;


public class PagedRssAdapter extends PagedListAdapter<Article,RssViewHolder> {

    private Context context;

    public PagedRssAdapter(@NonNull DiffUtil.ItemCallback<Article> diffCallback,Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public RssViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new RssViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull RssViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

}
