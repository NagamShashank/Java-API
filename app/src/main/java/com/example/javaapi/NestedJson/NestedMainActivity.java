package com.example.javaapi.NestedJson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.javaapi.ApiInterface;
import com.example.javaapi.NestedJson.ModelClasses.Nested_DataModelItem;
import com.example.javaapi.R;
import com.example.javaapi.databinding.ActivityNestedMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NestedMainActivity extends AppCompatActivity {


    String BaseUrl = "https://jsonplaceholder.typicode.com/";
    ActivityNestedMainBinding binding;

    List<Nested_DataModelItem> listOfUsers;

    NestedApiInterface nestedApiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNestedMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.NestedRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.NestedRecyclerView.setLayoutManager(layoutManager);

        getNestdata();


    }

    private void getNestdata() {

        Retrofit Nestretrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nestedApiInterface = Nestretrofit.create(NestedApiInterface.class);

        nestedApiInterface.getNestedData().enqueue(new Callback<List<Nested_DataModelItem>>() {
            @Override
            public void onResponse(Call<List<Nested_DataModelItem>> call, Response<List<Nested_DataModelItem>> response) {
                listOfUsers = response.body();
                Nested_Adapter nested_adapter = new Nested_Adapter(listOfUsers,getBaseContext());
                binding.NestedRecyclerView.setAdapter(nested_adapter);
                nested_adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Nested_DataModelItem>> call, Throwable t) {

            }
        });
    }
}