package com.example.alekseyignatenko.rss_reader.model;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

import com.example.alekseyignatenko.rss_reader.DateSorting.ArticleDateComparator;
import com.example.alekseyignatenko.rss_reader.NetWork.HabarAPI;
import com.example.alekseyignatenko.rss_reader.NetWork.MeduzaAPI;
import com.example.alekseyignatenko.rss_reader.NetWork.RetrofitService;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSource extends ItemKeyedDataSource<String,Article> {

    private final String MEDUZA_URL = "https://meduza.io/rss/podcasts/";
    private final String HABAR_URL ="https://habr.com/ru/rss/hubs/all/";

    private RetrofitService retrofitService = new RetrofitService();

    private List<Article> articles;

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        Call<RSSFeed> dataone = retrofitService.createService(HabarAPI.class,HABAR_URL).loadRSSFeed();
        dataone.enqueue(new Callback<RSSFeed>() {
            @Override
            public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {

                RSSFeed rssFeed = response.body();
                rssFeed.setUrlForAllArticles();
                articles = rssFeed.getArticleList();

                Call<RSSFeed> datatwo = retrofitService.createService(MeduzaAPI.class,MEDUZA_URL).loadRSSFeed();
                datatwo.enqueue(new Callback<RSSFeed>() {
                    @Override
                    public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {

                        RSSFeed rssFeed = response.body();
                        rssFeed.setUrlForAllArticles();
                        articles.addAll(rssFeed.getArticleList());
                        Collections.sort(articles,new ArticleDateComparator());

                        List<Article> result = new ArrayList<Article>();
                        for(int i=0;i<params.requestedLoadSize;i++){
                            Article article = articles.get(i);
                            //article.setImageUrl(url);
                            result.add(article);
                        }
                        if (params.placeholdersEnabled) {
                            callback.onResult(result, 0, articles.size());
                        }else {
                            callback.onResult(result);
                        }
                    }

                    @Override
                    public void onFailure(Call<RSSFeed> call, Throwable t) {

                    }
                });
            }
            @Override
            public void onFailure(Call<RSSFeed> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {

        List<Article> result = new ArrayList<Article>();
        int position = articles.size();
        for(int i =0;i<articles.size();i++){
            if(articles.get(i).getTitle().equals(params.key)) {
                position = i+1;
                break;
            }
        }

        int loadsize = params.requestedLoadSize+position;

        while (position<loadsize){
            if(position>=articles.size())break;
            Article article = articles.get(position);
            result.add(article);
            position++;
        }

        callback.onResult(result);
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

        List<Article> result = new ArrayList<Article>();
        int position = articles.size();
        for(int i =0;i<articles.size();i++){
            if(articles.get(i).getTitle().equals(params.key)) {
                position = i-1;
                break;
            }
        }

        int loadsize = position - params.requestedLoadSize;

        while (position>loadsize){
            if(position<0)break;
            Article article = articles.get(position);
            result.add(article);
            position--;
        }

        callback.onResult(result);
    }

    @NonNull
    @Override
    public String getKey(@NonNull Article item) {
        return item.getTitle();
    }
}
