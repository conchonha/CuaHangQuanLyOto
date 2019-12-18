package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.quanlyoto_doan.Adapter.ManagerAccount_Adapter;
import com.example.quanlyoto_doan.R;

public class Admin_Activity extends AppCompatActivity {
    private LinearLayout linnerFanpage,linnerCustommer,linnerLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);
        init();
    }

    private void init() {
        linnerFanpage=findViewById(R.id.linnerFanpage);
        linnerFanpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Fanpage_Activity.class));
            }
        });
        linnerCustommer=findViewById(R.id.linnerCustommer);
        linnerCustommer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ManagementAccount_Activity.class));
            }
        });
        linnerLogout=findViewById(R.id.linnerLogout);
        linnerLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.editor.remove("admin");
                LoginActivity.editor.putString("cusTomer","Customer");
                LoginActivity.editor.commit();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }
        });
    }
}
