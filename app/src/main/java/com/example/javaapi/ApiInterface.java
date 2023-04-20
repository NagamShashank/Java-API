package com.example.javaapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    // https://jsonplaceholder.typicode.com/posts

    @GET("posts")
    Call<List<MyData>> getData();

    @POST("posts")
    Call<MyData> sendData(@Body MyData myData);

}
