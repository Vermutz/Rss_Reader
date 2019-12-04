package com.example.alekseyignatenko.rss_reader.NetWork;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitService {

    public <S> S createService(Class<S> serviceClass,String BASE_URL) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
