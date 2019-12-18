package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyoto_doan.Adapter.ListServices_Adapter;
import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Services extends AppCompatActivity {
    private Toolbar tollbarServiecs;
    private TextView txtDate,txtTime,txtTypeServices,txtSpeedDometter;
    private EditText txtNode,edtAddress;
    private Button btnRegisterServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        init();
        setActionbar();
        setOnclickView();
        registerServices();
    }

    private void registerServices() {
        btnRegisterServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtDate.getText().toString().equals("") || txtTime.getText().toString().equals("") ||
                txtTypeServices.getText().toString().equals("") || txtSpeedDometter.getText().toString().equals("")
                || txtNode.getText().toString().equals("") || edtAddress.getText().toString().equals("") ||
                ListServices_Adapter.type==0){
                    Toast.makeText(Services.this, "Data not be blank", Toast.LENGTH_SHORT).show();
                }else{
                    DataService dataService=APIServices.getService();
                    Call<String>callback=dataService.registerServices(txtDate.getText().toString(),
                            txtTime.getText().toString(),txtSpeedDometter.getText().toString(),
                            txtNode.getText().toString(),ListServices_Adapter.type+"",edtAddress.getText().toString(),
                            LoginActivity.sharedPreferences.getInt("idacount",0)+"");
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("AAA","registerServices: "+response.toString());
                            if(response.isSuccessful()){
                                Toast.makeText(Services.this, "Register Succes", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("AAA","Erro: "+t.toString());
                        }
                    });

                }
            }
        });
    }

    private void setOnclickView() {
        final Calendar calendar=Calendar.getInstance();
        String time=calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE);
        txtTime.setText(time);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int date=calendar.get(Calendar.DATE);
                int month=calendar.get(Calendar.MONTH);
                int year=calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog=new DatePickerDialog(Services.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth) {
                        int mounth2=month1+1;
                        txtDate.setText(dayOfMonth+"/"+mounth2+"/"+year1);
                    }
                },year,month,date);
                datePickerDialog.show();
            }
        });
        txtTypeServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),List_Services.class));
                txtTypeServices.setText(ListServices_Adapter.Name+"");
            }
        });
        txtSpeedDometter.setText(HomeActivity.sharedPreferences.getInt("contermet",0)+"");
    }

    private void setActionbar() {
        setSupportActionBar(tollbarServiecs);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tollbarServiecs.setNavigationIcon(R.drawable.back);
        tollbarServiecs.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        txtSpeedDometter=findViewById(R.id.txtSpeedDometter);
        txtTypeServices=findViewById(R.id.txtTypeServices);
        txtTime=findViewById(R.id.txtTime);
        tollbarServiecs=findViewById(R.id.tollbarServiecs);
        txtDate=findViewById(R.id.txtDate);
        txtNode=findViewById(R.id.txtNode);
        edtAddress=findViewById(R.id.edtAddress);
        btnRegisterServices=findViewById(R.id.btnRegisterServices);
    }


}
