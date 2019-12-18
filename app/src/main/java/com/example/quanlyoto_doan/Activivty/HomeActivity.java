package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.quanlyoto_doan.Adapter.Banner_Adapter;
import com.example.quanlyoto_doan.Model.VerhicleInfomation;
import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbarMain;
    private DrawerLayout drawerlayout;
    private ViewPager viewpagermain;
    private Runnable runnable;
    private TextView txtNameCustomer,txtEmail,txtLogout,txtAccessoriesForCard,
            txtservices,txtVehicleInfomation,txtContactUs;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getDataVerhicleInformation();
        init();
        setActionBar();
        setViewPager();
        setInitNavigationView();
    }
    private void getDataVerhicleInformation() {
        DataService dataService= APIServices.getService();
        Call<List<VerhicleInfomation>> callback=dataService.checkAcount(LoginActivity.sharedPreferences.getInt("idacount",0)+"");
        callback.enqueue(new Callback<List<VerhicleInfomation>>() {
            @Override
            public void onResponse(Call<List<VerhicleInfomation>> call, Response<List<VerhicleInfomation>> response) {
                Log.d("AAA","CheckAcountVerhicleInfomation: "+response.toString());
                if(response.isSuccessful()){
                    Log.d("AAA","CheckAcountVerhicleInfomation: "+response.body());
                    ArrayList<VerhicleInfomation>arrayList= (ArrayList<VerhicleInfomation>) response.body();
                    editor.putInt("id",arrayList.get(0).getId());
                    editor.putString("producer",arrayList.get(0).getProducer());
                    editor.putString("type",arrayList.get(0).getType());
                    editor.putInt("capacity",arrayList.get(0).getCapacity());
                    editor.putInt("contermet",arrayList.get(0).getContermet());
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<List<VerhicleInfomation>> call, Throwable t) {
                startActivity(new Intent(getApplicationContext(),RegisterVerhicleInfomation.class));
                finish();
            }
        });

    }

    private void setInitNavigationView() {
        txtContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Contact_Activity.class));
            }
        });
        txtVehicleInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegisterVerhicleInfomation.class);
                intent.putExtra("VehicleInfomation","VehicleInfomation");
                startActivity(intent);
            }
        });
        txtservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Services.class));
            }
        });
        txtAccessoriesForCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ProductActivity.class);
                intent.putExtra("typeProduct","Accessories");
                startActivity(intent);
            }
        });
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.editor.remove("cusTomer");
                LoginActivity.editor.commit();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        txtNameCustomer.setText(LoginActivity.sharedPreferences.getString("fullName",""));
        txtEmail.setText(LoginActivity.sharedPreferences.getString("email",""));
    }

    private void setViewPager() {
        String []arrayPicture={
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIIPc3XBLJLBw5aboPfWCv0DKcM1-DagBc3fULjpjLTVngbZrO&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSjQAHBz9fNJeFAX6wwt2m2hhnpvRqsEDQTuUdxf6OIPrzSAi48&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIIPc3XBLJLBw5aboPfWCv0DKcM1-DagBc3fULjpjLTVngbZrO&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOH9sEf4KSVvcLHe33uoD5WN1-csKouSm4_IaMqaRoEeigPo8F&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSN7UbnndYve_arOcFOnKPrso9vGkDTV-BLbUEGc1FbLzy9XSXvOw&s"
        };
        Banner_Adapter adapter=new Banner_Adapter(getApplicationContext(),arrayPicture);
        viewpagermain.setAdapter(adapter);
       final Handler handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
               int  CurrentItem=viewpagermain.getCurrentItem();
                CurrentItem++;
                if(CurrentItem>=viewpagermain.getAdapter().getCount()){
                    CurrentItem=0;
                }
                viewpagermain.setCurrentItem(CurrentItem,true);
                handler.postDelayed(runnable,4500);
            }
        };
        handler.postDelayed(runnable,4500);
    }

    private void setActionBar() {
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarMain.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void init() {
        txtContactUs=findViewById(R.id.txtContactUs);
        sharedPreferences=getSharedPreferences("vehicleinfomation",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        txtservices=findViewById(R.id.txtservices);
        txtAccessoriesForCard=findViewById(R.id.txtAccessoriesForCard);
        txtEmail=findViewById(R.id.txtgmail);
        viewpagermain=findViewById(R.id.viewpagermain);
        toolbarMain=findViewById(R.id.toolbarMain);
        drawerlayout=findViewById(R.id.drawerlayout);
        txtNameCustomer=findViewById(R.id.txttennguoidung);
        txtLogout=findViewById(R.id.txtdangxuat);
        txtVehicleInfomation=findViewById(R.id.txtVehicleInfomation);
    }
}
