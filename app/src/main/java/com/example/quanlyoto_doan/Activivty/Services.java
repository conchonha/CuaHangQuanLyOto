package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        getDataVerhicleInformation();
    }

    private void getDataVerhicleInformation() {
        DataService dataService= APIServices.getService();
        Call<String>callback=dataService.checkAcount(LoginActivity.sharedPreferences.getInt("idacount",0)+"");
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("AAA","checkVerhicleInfomation: "+response.toString());
                if(response.isSuccessful()){
                    Log.d("AAA","checkVerhicleInformation: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                startActivity(new Intent(getApplicationContext(),RegisterVerhicleInfomation.class));
            }
        });
    }
}
