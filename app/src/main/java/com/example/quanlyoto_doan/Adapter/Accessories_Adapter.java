package com.example.quanlyoto_doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyoto_doan.Activivty.ProductActivity;
import com.example.quanlyoto_doan.Activivty.ProductDetails;
import com.example.quanlyoto_doan.Model.Accessory;
import com.example.quanlyoto_doan.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Accessories_Adapter extends RecyclerView.Adapter<Accessories_Adapter.Accessories_Viewhdler> {
    private Context context;
    private int layout;
    private ArrayList<Accessory>arrayList;
    private View view;

    public Accessories_Adapter(Context context, int layout, ArrayList<Accessory> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Accessories_Viewhdler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new Accessories_Viewhdler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Accessories_Viewhdler holder, int position) {
        Picasso.with(context).load(arrayList.get(position).getPicture()).into(holder.imgProduct);
        holder.txtNameProduct.setText(arrayList.get(position).getName());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtPriceProduct.setText(decimalFormat.format(arrayList.get(position).getPrice())+"Đ");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Accessories_Viewhdler extends RecyclerView.ViewHolder{
        private TextView txtNameProduct,txtPriceProduct;
        private ImageView imgProduct;
        public Accessories_Viewhdler(@NonNull View itemView) {
            super(itemView);
            txtNameProduct=itemView.findViewById(R.id.txtNameProduct);
            txtPriceProduct=itemView.findViewById(R.id.txtPriceProduct);
            imgProduct=itemView.findViewById(R.id.imgProduct);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ProductDetails.class);
                    intent.putExtra("Accessories",arrayList.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
