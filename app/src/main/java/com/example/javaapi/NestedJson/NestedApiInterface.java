package com.example.javaapi.NestedJson;


import com.example.javaapi.NestedJson.ModelClasses.Nested_DataModelItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NestedApiInterface {

    // https://jsonplaceholder.typicode.com/users

    @GET("users")
    Call<List<Nested_DataModelItem>> getNestedData();
}
