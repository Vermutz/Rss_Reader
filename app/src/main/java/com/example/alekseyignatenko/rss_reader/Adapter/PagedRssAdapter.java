package com.example.alekseyignatenko.rss_reader.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alekseyignatenko.rss_reader.R;
import com.example.alekseyignatenko.rss_reader.model.RssObject;

import java.util.ArrayList;
import java.util.Collection;

public class PagedRssAdapter extends PagedListAdapter<RssObject,RssViewHolder> {


    public PagedRssAdapter(@NonNull DiffUtil.ItemCallback<RssObject> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RssViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new RssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RssViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

}
