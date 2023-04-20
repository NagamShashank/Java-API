package com.example.javaapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.javaapi.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    ActivityMainBinding binding;

    List<MyData> list;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.RecyclerViewUsers.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.RecyclerViewUsers.setLayoutManager(layoutManager);




        getMyData();

    }

    private void getMyData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        apiInterface.getData().enqueue(new Callback<List<MyData>>() {
            @Override
            public void onResponse(Call<List<MyData>> call, Response<List<MyData>> response) {
//                assert response.body() != null;
//                list.addAll(response.body());
                list = response.body();
                MyAdapter myAdapter = new MyAdapter(response.body(),getBaseContext());
                binding.RecyclerViewUsers.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<MyData>> call, Throwable t) {

            }
        });


    }
}