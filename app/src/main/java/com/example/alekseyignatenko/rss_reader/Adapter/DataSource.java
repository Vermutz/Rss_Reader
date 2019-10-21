package com.example.alekseyignatenko.rss_reader.Adapter;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.example.alekseyignatenko.rss_reader.model.RssObject;

import java.util.ArrayList;

public class DataSource extends PositionalDataSource<RssObject> {

    private ArrayList<RssObject> RssItemList;

    public DataSource(ArrayList<RssObject> rssItemList) {
        RssItemList = rssItemList;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<RssObject> callback) {
        ArrayList<RssObject> Result = new ArrayList<RssObject>();
        for (int i=0;i<params.requestedLoadSize;i++){
            Result.add(RssItemList.get(i));
        }
        callback.onResult(Result, 0);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<RssObject> callback) {
        ArrayList<RssObject> Result = new ArrayList<RssObject>();
        for (int i=params.startPosition;i<(params.startPosition+params.loadSize);i++){
            Result.add(RssItemList.get(i));
        }
        callback.onResult(Result);
    }
}
