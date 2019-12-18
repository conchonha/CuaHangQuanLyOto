package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.quanlyoto_doan.Adapter.ListServices_Adapter;
import com.example.quanlyoto_doan.Model.Service;
import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_Services extends AppCompatActivity {
    private Toolbar toolBarListServices;
    private RecyclerView recyclerViewListServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__services);
        setActionBarr();
        getDataListServices();
    }

    private void getDataListServices() {
        DataService dataService= APIServices.getService();
        final Call<List<Service>>callback=dataService.getDataListServices();
        callback.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                Log.d("AAA","getDataListServices: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<Service>arrayList= (ArrayList<Service>) response.body();
                    setRecyclerViewListServices(arrayList);
                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {

            }
        });
    }

    private void setRecyclerViewListServices(ArrayList<Service>arrayList){
        recyclerViewListServices=findViewById(R.id.recyclerViewListServices);
        recyclerViewListServices.setHasFixedSize(true);
        recyclerViewListServices.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        ListServices_Adapter adapter=new ListServices_Adapter(List_Services.this,R.layout.layout_list_services,arrayList);
        recyclerViewListServices.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void close(){
        finish();
    }

    private void setActionBarr() {
        toolBarListServices=findViewById(R.id.toolBarListServices);
        setSupportActionBar(toolBarListServices);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolBarListServices.setNavigationIcon(R.drawable.back);
        toolBarListServices.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
