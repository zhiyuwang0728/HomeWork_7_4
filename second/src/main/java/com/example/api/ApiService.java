package com.example.api;

import com.example.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    String baseUrl="https://www.wanandroid.com/project/";
    @GET("list/1/json?cid=294")
    Observable<Bean> getData();
}
