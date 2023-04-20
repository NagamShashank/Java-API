package com.example.javaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.javaapi.databinding.ActivityMain2Binding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {


    String BASE_URL_SEND = "https://jsonplaceholder.typicode.com/";
    ActivityMain2Binding binding;

    ApiInterface apiInterface_Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.ViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofitSend = new Retrofit.Builder()
                .baseUrl(BASE_URL_SEND)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface_Send = retrofitSend.create(ApiInterface.class);

        binding.sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String User_ID = binding.EdittextUserID.getText().toString();
                String User_id = binding.EdittextID.getText().toString();
                String User_Title = binding.EdittextTitle.getText().toString();
                String User_Body = binding.EdittextBody.getText().toString();

                if(    ((User_ID.isEmpty()) && (User_id.isEmpty()) ) && (User_Title.isEmpty()  &&  User_Body.isEmpty())   ){
                    Toast.makeText(getApplicationContext(),"Please Enter Whole Information",Toast.LENGTH_SHORT).show();
                }
                else{
                    int newUserId = Integer.parseInt(User_ID);
                    int newUserid = Integer.parseInt(User_id);
                    MyData mydata = new MyData(newUserId,newUserid,User_Title,User_Body);
                    apiInterface_Send.sendData(mydata).enqueue(new Callback<MyData>() {
                        @Override
                        public void onResponse(Call<MyData> call, Response<MyData> response) {
                            Toast.makeText(getApplicationContext(),"Data Sended",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<MyData> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Data Not Sended",Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
        });

    }


}



//