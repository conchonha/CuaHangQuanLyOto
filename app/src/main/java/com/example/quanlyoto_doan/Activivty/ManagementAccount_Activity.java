package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.quanlyoto_doan.Adapter.ManagerAccount_Adapter;
import com.example.quanlyoto_doan.Model.Account;
import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagementAccount_Activity extends AppCompatActivity {
    private Toolbar toolBarAccount;
    private RecyclerView recyclerViewListAcount;
    private ImageView imgMenuManagerAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_account_);
        init();
        getDataManagerListAccount();
        setMenuIcon();
    }

    private void setMenuIcon() {
        imgMenuManagerAccount=findViewById(R.id.imgMenuManagerAccount);
        imgMenuManagerAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(ManagementAccount_Activity.this,imgMenuManagerAccount);
                popupMenu.getMenuInflater().inflate(R.menu.menu_add,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menuCustomer:
                                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                                intent.putExtra("Customer","Customer");
                                startActivity(intent);
                                break;
                            case R.id.menuEmployees:
                                Intent intent1=new Intent(getApplicationContext(),RegisterActivity.class);
                                intent1.putExtra("Employees","Employees");
                                startActivity(intent1);
                                break;
                            case R.id.loyalcustomers:
                                Intent intent2=new Intent(getApplicationContext(),RegisterActivity.class);
                                intent2.putExtra("loyalcustomers","loyalcustomers");
                                startActivity(intent2);
                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    private void getDataManagerListAccount() {
        DataService dataService= APIServices.getService();
        Call<List<Account>>callback=dataService.getDataManagerAccount();
        callback.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                Log.d("AAA","getDataAccountManager: "+response.toString());
                if(response.isSuccessful()){
                    Log.d("AAA","getDataAccountManager: "+response.body());
                    ArrayList<Account>arrayList= (ArrayList<Account>) response.body();
                    setRecyclerViewListAcountt(arrayList);
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {

            }
        });
    }

    private void setRecyclerViewListAcountt(ArrayList<Account>arrayList) {
        recyclerViewListAcount=findViewById(R.id.recyclerViewListAcount);
        recyclerViewListAcount.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerViewListAcount.setHasFixedSize(true);
        ManagerAccount_Adapter adapter=new ManagerAccount_Adapter(getApplicationContext(),R.layout.layout_manager_account,arrayList);
        recyclerViewListAcount.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void init() {
        toolBarAccount=findViewById(R.id.toolBarAccount);
        setSupportActionBar(toolBarAccount);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolBarAccount.setNavigationIcon(R.drawable.back);
        toolBarAccount.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
