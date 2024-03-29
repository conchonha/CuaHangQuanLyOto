package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.quanlyoto_doan.Adapter.Main_Adapter;
import com.example.quanlyoto_doan.Fragment.Fragment_Slider1;
import com.example.quanlyoto_doan.Fragment.Fragment_Slider2;
import com.example.quanlyoto_doan.Fragment.Fragment_Slider3;
import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewPager mainviewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            anhxa();
    }


    private void anhxa() {
        mainviewpager=findViewById(R.id.mainviewpager);
        Main_Adapter adapter=new Main_Adapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Slider1(),"fragmentTitle1");
        adapter.addFragment(new Fragment_Slider2(),"fragmentTitle2");
        adapter.addFragment(new Fragment_Slider3(),"fragmentTitle3");
        mainviewpager.setAdapter(adapter);
    }
}
