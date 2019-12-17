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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Services extends AppCompatActivity {
    private Toolbar tollbarServiecs;
    private TextView txtDate,txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        init();
        setActionbar();
        setOnclickView();
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
        txtTime=findViewById(R.id.txtTime);
        tollbarServiecs=findViewById(R.id.tollbarServiecs);
        txtDate=findViewById(R.id.txtDate);
    }


}
