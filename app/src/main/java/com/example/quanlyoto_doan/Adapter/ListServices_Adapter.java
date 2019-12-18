package com.example.quanlyoto_doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyoto_doan.Activivty.List_Services;
import com.example.quanlyoto_doan.Activivty.Services;
import com.example.quanlyoto_doan.Model.Service;
import com.example.quanlyoto_doan.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListServices_Adapter extends RecyclerView.Adapter <ListServices_Adapter.ViewhdlerListServices>{
    private List_Services context;
    private int layout;
    private ArrayList<Service>arrayList;
    private View view;
    public static String Name="";
    public  static int type=0;

    public ListServices_Adapter(List_Services context, int layout, ArrayList<Service> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewhdlerListServices onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new ViewhdlerListServices(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewhdlerListServices holder, int position) {
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtNameItemServices.setText(arrayList.get(position).getNameservices());
        holder.txtPriceItemServices.setText(decimalFormat.format(arrayList.get(position).getPriceservices())+"Đ");
        Picasso.with(context).load(arrayList.get(position).getPictureicon()).into(holder.imgItemServices);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewhdlerListServices extends RecyclerView.ViewHolder{
        private TextView txtNameItemServices,txtPriceItemServices;
        private ImageView imgItemServices;
        public ViewhdlerListServices(@NonNull View itemView) {
            super(itemView);
            txtNameItemServices=itemView.findViewById(R.id.txtNameItemServices);
            txtPriceItemServices=itemView.findViewById(R.id.txtPriceItemServices);
            imgItemServices=itemView.findViewById(R.id.imgItemServices);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type=arrayList.get(getPosition()).getId();
                    Name=arrayList.get(getPosition()).getNameservices();
                    context.close();
                }
            });
        }
    }
}
