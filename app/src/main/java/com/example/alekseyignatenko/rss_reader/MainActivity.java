package com.example.alekseyignatenko.rss_reader;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.alekseyignatenko.rss_reader.Adapter.PagedRssAdapter;
import com.example.alekseyignatenko.rss_reader.NetWork.MyCallBack;


public class MainActivity extends AppCompatActivity {

    private RecyclerView listViewRSS;
    private NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewRSS = (RecyclerView) findViewById(R.id.ListViewRSS);

        listViewRSS.setLayoutManager(new LinearLayoutManager(this));

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        newsViewModel.getPagedListLiveData().observe(this, articles -> {
            PagedRssAdapter pagedRssAdapter = new PagedRssAdapter(new MyCallBack(),this);
            pagedRssAdapter.submitList(articles);
            listViewRSS.setAdapter(pagedRssAdapter);
        });
    }



}
