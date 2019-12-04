package com.example.alekseyignatenko.rss_reader;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.alekseyignatenko.rss_reader.model.DataSource;
import com.example.alekseyignatenko.rss_reader.model.MySourceFactory;
import com.example.alekseyignatenko.rss_reader.model.Article;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewsViewModel extends AndroidViewModel {

    private MutableLiveData<DataSource> mutableLiveData;
    private MySourceFactory mySourceFactory;
    private Executor executor;
    private LiveData<PagedList<Article>> pagedListLiveData;


    public NewsViewModel(@NonNull Application application) {
        super(application);

        mySourceFactory = new MySourceFactory();
        mutableLiveData = mySourceFactory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPageSize(15)
                .setPrefetchDistance(8)
                .build();

        executor = Executors.newFixedThreadPool(5);

        pagedListLiveData = new LivePagedListBuilder<Long,Article>(mySourceFactory,config)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Article>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
