package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.quanlyoto_doan.Adapter.Banner_Adapter;
import com.example.quanlyoto_doan.R;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbarMain;
    private DrawerLayout drawerlayout;
    private ViewPager viewpagermain;
    private Runnable runnable;
    private TextView txtNameCustomer,txtEmail,txtLogout,txtAccessoriesForCard,
            txtservices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setActionBar();
        setViewPager();
        setInitNavigationView();
    }

    private void setInitNavigationView() {
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
        txtservices=findViewById(R.id.txtservices);
        txtAccessoriesForCard=findViewById(R.id.txtAccessoriesForCard);
        txtEmail=findViewById(R.id.txtgmail);
        viewpagermain=findViewById(R.id.viewpagermain);
        toolbarMain=findViewById(R.id.toolbarMain);
        drawerlayout=findViewById(R.id.drawerlayout);
        txtNameCustomer=findViewById(R.id.txttennguoidung);
        txtLogout=findViewById(R.id.txtdangxuat);
    }
}
