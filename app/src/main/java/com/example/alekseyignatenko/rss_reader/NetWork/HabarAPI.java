package com.example.alekseyignatenko.rss_reader.NetWork;

import androidx.paging.PagedList;
import androidx.paging.PagedList.BoundaryCallback;

import com.example.alekseyignatenko.rss_reader.model.RSSFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HabarAPI {

    @GET("feed.rss")
    Call<RSSFeed> loadRSSFeed();


}
