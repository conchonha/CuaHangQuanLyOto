package com.example.quanlyoto_doan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyoto_doan.Activivty.ProductActivity;
import com.example.quanlyoto_doan.Adapter.Accessories_Adapter;
import com.example.quanlyoto_doan.Model.Accessory;
import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAccessories extends Fragment {
    private View view;
    private RecyclerView recyclerviewAccessories;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_accessories,container,false);
        getDataAccessories();
        setSeeMore();
        return view;
    }

    private void setSeeMore() {
        TextView txtSeeMore=view.findViewById(R.id.txtSeeMore);
        txtSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), ProductActivity.class);
                intent.putExtra("typeProduct","Accessories");
                startActivity(intent);
            }
        });
    }

    private void getDataAccessories() {
        DataService dataService= APIServices.getService();
        Call<List<Accessory>>callback=dataService.getDataAccessories();
        callback.enqueue(new Callback<List<Accessory>>() {
            @Override
            public void onResponse(Call<List<Accessory>> call, Response<List<Accessory>> response) {
                Log.d("AAA","getDataAccessories: "+response.toString());
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    ArrayList<Accessory>arrayList= (ArrayList<Accessory>) response.body();
                    setRecyclerview(arrayList);
                }
            }

            @Override
            public void onFailure(Call<List<Accessory>> call, Throwable t) {

            }
        });
    }

    private void setRecyclerview(ArrayList<Accessory>arrayList) {
        recyclerviewAccessories=view.findViewById(R.id.recyclerviewAccessories);
        recyclerviewAccessories.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        recyclerviewAccessories.setHasFixedSize(true);
        Accessories_Adapter adapter=new Accessories_Adapter(view.getContext(),R.layout.layout_product,
                arrayList);
        recyclerviewAccessories.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
