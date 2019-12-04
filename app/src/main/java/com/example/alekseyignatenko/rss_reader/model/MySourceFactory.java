package com.example.alekseyignatenko.rss_reader.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

;

public class MySourceFactory extends DataSource.Factory {

    com.example.alekseyignatenko.rss_reader.model.DataSource DataSource;
    MutableLiveData<com.example.alekseyignatenko.rss_reader.model.DataSource> mutableLiveData;

    public MySourceFactory() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        this.DataSource = new com.example.alekseyignatenko.rss_reader.model.DataSource();
        this.mutableLiveData.postValue(this.DataSource);
        return this.DataSource;
    }

    public MutableLiveData<com.example.alekseyignatenko.rss_reader.model.DataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
