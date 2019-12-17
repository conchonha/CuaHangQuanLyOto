package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.quanlyoto_doan.Adapter.Banner_Adapter;
import com.example.quanlyoto_doan.Model.Accessory;
import com.example.quanlyoto_doan.R;

public class ProductDetails extends AppCompatActivity {
    private ViewPager viewpagerchitietsanpham;
    private TextView txttensanpham1,txtgiasanpham1,txtngaydang1,txttrinhtrang,txtmota1;
    private Toolbar toolBarProductDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityproduct_details);
        anhxa();
        setActionbar();
        getdata();
    }

    private void setActionbar() {
        setSupportActionBar(toolBarProductDetails);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolBarProductDetails.setNavigationIcon(R.drawable.back);
        toolBarProductDetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getdata() {
        Intent intent=getIntent();
        if(intent!=null){
            Accessory accessory= (Accessory) intent.getSerializableExtra("Accessories");
            String []arrayPicture=accessory.getPicturedesscription().split("@");
            Log.d("AAA","aray picture: "+arrayPicture[1]);
            Banner_Adapter adapter=new Banner_Adapter(getApplicationContext(),arrayPicture);
            viewpagerchitietsanpham.setAdapter(adapter);
            txttensanpham1.setText(accessory.getName());
            txtgiasanpham1.setText(accessory.getPrice()+"Đ");
            txtmota1.setText(accessory.getDescription());
            txttrinhtrang.setText("still");
        }
    }

    private void anhxa() {
        toolBarProductDetails=findViewById(R.id.toolBarProductDetails);
        viewpagerchitietsanpham=findViewById(R.id.viewpagerchitietsanpham);
        txttensanpham1=findViewById(R.id.txttensanpham1);
        txtgiasanpham1=findViewById(R.id.txtgiasanpham1);

        txttrinhtrang=findViewById(R.id.txttrinhtrang);
        txtmota1=findViewById(R.id.txtmota1);
    }
}
