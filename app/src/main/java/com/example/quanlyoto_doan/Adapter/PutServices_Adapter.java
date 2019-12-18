package com.example.quanlyoto_doan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyoto_doan.Model.PutForService;
import com.example.quanlyoto_doan.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PutServices_Adapter extends RecyclerView.Adapter <PutServices_Adapter.ViewhdlerPutServices>{
    private Context context;
    private int layout;
    private ArrayList<PutForService>arrayList;
    private View view;

    public PutServices_Adapter(Context context, int layout, ArrayList<PutForService> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewhdlerPutServices onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new ViewhdlerPutServices(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewhdlerPutServices holder, int position) {
        PutForService putForService=arrayList.get(position);
        holder.txtNameServices.setText(putForService.getNameservices());
        holder.txtNotePutServices.setText(putForService.getNote());
        holder.txtPutDate.setText(putForService.getPutdate());
        holder.txtPutTime.setText(putForService.getTime());
        holder.txtSpeeDoMeter.setText(putForService.getSpeedometer()+"km");
        Picasso.with(context).load(putForService.getPictureicon()).into(holder.roundImageViewPutForServices);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewhdlerPutServices extends RecyclerView.ViewHolder{
        private TextView txtNameServices,txtSpeeDoMeter,txtNotePutServices,txtPutDate,txtPutTime;
        private RoundedImageView roundImageViewPutForServices;
        public ViewhdlerPutServices(@NonNull View itemView) {
            super(itemView);
            txtNameServices=itemView.findViewById(R.id.txtNameServices);
            txtSpeeDoMeter=itemView.findViewById(R.id.txtSpeeDoMeter);
            txtNotePutServices=itemView.findViewById(R.id.txtNotePutServices);
            txtPutDate=itemView.findViewById(R.id.txtPutDate);
            txtPutTime=itemView.findViewById(R.id.txtPutTime);
            roundImageViewPutForServices=itemView.findViewById(R.id.roundImageViewPutForServices);
        }
    }
}
