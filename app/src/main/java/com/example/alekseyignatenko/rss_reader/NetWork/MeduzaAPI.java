package com.example.alekseyignatenko.rss_reader.NetWork;

import com.example.alekseyignatenko.rss_reader.model.RSSFeed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MeduzaAPI {

    @GET("meduza-v-kurse")
    Call<RSSFeed> loadRSSFeed();
}
