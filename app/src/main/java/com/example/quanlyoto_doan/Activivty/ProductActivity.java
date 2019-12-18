package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quanlyoto_doan.Adapter.Accessories_Adapter;
import com.example.quanlyoto_doan.Adapter.PutServices_Adapter;
import com.example.quanlyoto_doan.Model.Accessory;
import com.example.quanlyoto_doan.Model.PutForService;
import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    private RecyclerView recyclerviewProduct;
    private Toolbar toolBarProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setActionBar();
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("typeProduct")){
                getDataAccessories();
            }
            if(intent.hasExtra("PutServices")){
                getDataPutServices();
            }
        }
    }
    private void setRecyclerView(ArrayList<PutForService>arrayList) {
        toolBarProduct.setTitle("History Services");
        recyclerviewProduct=findViewById(R.id.recyclerviewProduct);
        recyclerviewProduct.setHasFixedSize(true);
        recyclerviewProduct.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        PutServices_Adapter adapter=new PutServices_Adapter(getApplicationContext(),R.layout.layout_putforservices,arrayList);
        recyclerviewProduct.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void getDataPutServices() {
        DataService dataServics = APIServices.getService();
        Call<List<PutForService>>callback=dataServics.getDataPutServices(LoginActivity.sharedPreferences.
                getInt("idacount",0)+"","1");
        callback.enqueue(new Callback<List<PutForService>>() {
            @Override
            public void onResponse(Call<List<PutForService>> call, Response<List<PutForService>> response) {
                Log.d("AAA","getDataPutServices: "+response.toString());
                if(response.isSuccessful()){
                    Log.d("AAA","getDataPutServices: "+response.body());
                    ArrayList<PutForService>arrayList= (ArrayList<PutForService>) response.body();
                    setRecyclerView(arrayList);
                }
            }

            @Override
            public void onFailure(Call<List<PutForService>> call, Throwable t) {

            }
        });
    }

    private void setActionBar() {
        toolBarProduct=findViewById(R.id.toolBarProduct);
        setSupportActionBar(toolBarProduct);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolBarProduct.setNavigationIcon(R.drawable.back);
        toolBarProduct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataAccessories(){
        DataService dataService= APIServices.getService();
        Call<List<Accessory>>callback=dataService.getDataAccessoriesAll();
        callback.enqueue(new Callback<List<Accessory>>() {
            @Override
            public void onResponse(Call<List<Accessory>> call, Response<List<Accessory>> response) {
                Log.d("AAA","getDataAccessoriesAll: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<Accessory>arrayList= (ArrayList<Accessory>) response.body();
                    setRecyclerview(arrayList);
                    Toast.makeText(ProductActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Accessory>> call, Throwable t) {

            }
        });
    }
    private void setRecyclerview(ArrayList<Accessory> arrayList) {
        recyclerviewProduct=findViewById(R.id.recyclerviewProduct);
        recyclerviewProduct.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerviewProduct.setHasFixedSize(true);
        Accessories_Adapter adapter=new Accessories_Adapter(getApplicationContext(),R.layout.layout_current_product,
                arrayList);
        recyclerviewProduct.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
