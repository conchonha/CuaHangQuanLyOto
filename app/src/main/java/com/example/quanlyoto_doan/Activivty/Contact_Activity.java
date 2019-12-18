package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.quanlyoto_doan.R;

public class Contact_Activity extends AppCompatActivity {
    private Toolbar toolBarContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_);
        toolBarContacts=findViewById(R.id.toolBarContacts);
        setSupportActionBar(toolBarContacts);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolBarContacts.setNavigationIcon(R.drawable.back);
        toolBarContacts.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
