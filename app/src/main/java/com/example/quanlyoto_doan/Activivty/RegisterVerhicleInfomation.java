package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterVerhicleInfomation extends AppCompatActivity {
    private ImageView imgProducer,imgType,imgCapacity;
    private TextView txtProducer,txtType,txtCapacity;
    private EditText edtNumberContermetPresent;
    private Button btnRegister;
    private Toolbar toolbarVehicleInfomation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_verhicle_infomation);
        init();
        setActionBar();
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("VehicleInfomation")){
                setdata();
                txtProducer.setText(HomeActivity.sharedPreferences.getString("producer",""));
                txtType.setText(HomeActivity.sharedPreferences.getString("type",""));
                txtCapacity.setText(HomeActivity.sharedPreferences.getInt("capacity",0)+"");
                edtNumberContermetPresent.setText(HomeActivity.sharedPreferences.getInt("contermet",0)+"");
                btnRegister.setText("Update");
                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UpdateVerhicleInformation();
                    }
                });
            }else{
                setdata();
                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registerVerhicleInfomation();
                    }
                });
            }
        }

    }

    private void setActionBar() {
        setSupportActionBar(toolbarVehicleInfomation);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarVehicleInfomation.setNavigationIcon(R.drawable.back);
        toolbarVehicleInfomation.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void UpdateVerhicleInformation() {
        if(txtCapacity.getText().toString().equals("") || txtType.getText().toString().equals("") ||
                txtProducer.getText().toString().equals("") || edtNumberContermetPresent.getText().toString().equals("")){
            Toast.makeText(this, "Data may not be blank\n", Toast.LENGTH_SHORT).show();
        }else{
            DataService dataService= APIServices.getService();
            Call<String>callback=dataService.UpdateVehicleInfomation(txtProducer.getText().toString(), 
               txtType.getText().toString(),txtCapacity.getText().toString(),edtNumberContermetPresent
                .getText().toString(),HomeActivity.sharedPreferences.getInt("id",0)+"");
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("AAA","UpdateVehicleInfomation: "+response.toString());
                    if(response.isSuccessful()){
                        Log.d("AAA","UpdateVehicleInfomation: "+response.body());
                        Toast.makeText(RegisterVerhicleInfomation.this, "Update Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(RegisterVerhicleInfomation.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void registerVerhicleInfomation() {
        if(txtCapacity.getText().toString().equals("") || txtType.getText().toString().equals("") ||
        txtProducer.getText().toString().equals("") || edtNumberContermetPresent.getText().toString().equals("")){
            Toast.makeText(this, "Data may not be blank\n", Toast.LENGTH_SHORT).show();
        }else{
            DataService dataService= APIServices.getService();
            Call<String>callback=dataService.RegisterVehicleInfomation(txtProducer.getText().toString(),
                    txtType.getText().toString(),txtCapacity.getText().toString(),edtNumberContermetPresent
            .getText().toString(),LoginActivity.sharedPreferences.getInt("idacount",0)+"");
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("AAA","RegisterVehicleInfomation: "+response.toString());
                    if(response.isSuccessful()){
                        Log.d("AAA","RegisterVehicleInfomation: "+response.body());
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }

    private void setdata() {
        imgCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(RegisterVerhicleInfomation.this,imgCapacity);
                popupMenu.getMenuInflater().inflate(R.menu.menu_capacity,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.muoi:
                                txtCapacity.setText("10");
                                break;
                            case R.id.haimuoi:
                                txtCapacity.setText("20");
                                break;
                            case R.id.bamuoi:
                                txtCapacity.setText("30");
                                break;
                            case R.id.bonmuoi:
                                txtCapacity.setText("40");
                                break;
                            case R.id.nammuoi:
                                txtCapacity.setText("50");
                                break;

                        }
                        return false;
                    }
                });
            }
        });
        imgProducer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(RegisterVerhicleInfomation.this,imgProducer);
                popupMenu.getMenuInflater().inflate(R.menu.menu_producer,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mercerdesbenz:
                                txtProducer.setText("Mercerdes Benz");
                                break;
                            case R.id.honda:
                                txtProducer.setText("Honda");
                                break;
                            case R.id.yamaha:
                                txtProducer.setText("Yamaha");
                                break;
                            case R.id.toyota:
                                txtProducer.setText("Toyota");
                                break;
                            case R.id.suzuki:
                                txtProducer.setText("Suzuki");
                                break;

                        }
                        return false;
                    }
                });
            }
        });
        imgType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(RegisterVerhicleInfomation.this,imgType);
                popupMenu.getMenuInflater().inflate(R.menu.menu_type,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.car:
                                txtType.setText("Car");
                                break;
                            case R.id.pickuptruck:
                                txtType.setText("Pickup truck");
                                break;
                            case R.id.truck:
                                txtType.setText("Truck");
                                break;
                            case R.id.specializedvehicles:
                                txtType.setText("Specialized vehicles");
                                break;

                        }
                        return false;
                    }
                });
            }
        });
    }

    private void init() {
        toolbarVehicleInfomation=findViewById(R.id.toolbarVehicleInfomation);
        edtNumberContermetPresent=findViewById(R.id.edtNumberContermetPresent);
        imgType=findViewById(R.id.imgType);
        txtType=findViewById(R.id.txtType);
        imgProducer=findViewById(R.id.imgProducer);
        txtProducer=findViewById(R.id.txtProducer);
        imgCapacity=findViewById(R.id.imgCapacity);
        txtCapacity=findViewById(R.id.txtCapacity);
        btnRegister=findViewById(R.id.btnRegister);
    }
}
