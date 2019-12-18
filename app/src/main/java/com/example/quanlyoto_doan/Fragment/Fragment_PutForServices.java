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

import com.example.quanlyoto_doan.Activivty.Admin_Activity;
import com.example.quanlyoto_doan.Activivty.LoginActivity;
import com.example.quanlyoto_doan.Activivty.ProductActivity;
import com.example.quanlyoto_doan.Adapter.PutServices_Adapter;
import com.example.quanlyoto_doan.Model.PutForService;
import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PutForServices extends Fragment {
    private View view;
    private RecyclerView recyclerviewPutServices;
    private TextView txtSeeMoreHistory;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_putforservices,container,false);
        getDataPutServices();
        return view;
    }

    private void getDataPutServices() {
        DataService dataServics = APIServices.getService();
        Call<List<PutForService>>callback=dataServics.getDataPutServices(LoginActivity.sharedPreferences.
                getInt("idacount",0)+"","0");

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

    private void setRecyclerView(ArrayList<PutForService>arrayList) {
        txtSeeMoreHistory=view.findViewById(R.id.txtSeeMoreHistory);
        txtSeeMoreHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ProductActivity.class);
                intent.putExtra("PutServices","PutServices");
                startActivity(intent);
            }
        });
        recyclerviewPutServices=view.findViewById(R.id.recyclerviewPutServices);
        recyclerviewPutServices.setHasFixedSize(true);
        recyclerviewPutServices.setLayoutManager(new GridLayoutManager(getContext(),1));
        PutServices_Adapter adapter=new PutServices_Adapter(getActivity(),R.layout.layout_putforservices,arrayList);
        recyclerviewPutServices.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
