package com.example.quanlyoto_doan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyoto_doan.Model.Account;
import com.example.quanlyoto_doan.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ManagerAccount_Adapter extends RecyclerView.Adapter<ManagerAccount_Adapter.ViewholderManagerAccount> {
    private Context context;
    private int layout;
    private ArrayList<Account>arrayList;
    private View view;

    public ManagerAccount_Adapter(Context context, int layout, ArrayList<Account> accounts) {
        this.context = context;
        this.layout = layout;
        this.arrayList = accounts;
    }

    @NonNull
    @Override
    public ViewholderManagerAccount onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new ViewholderManagerAccount(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewholderManagerAccount holder, int position) {
        Account account=arrayList.get(position);
        holder.txtAccountCode.setText(account.getId()+"");
        holder.txtEmail.setText(account.getEmail());
        holder.txtFullName.setText(account.getFullname());
        holder.txtIdentityCard.setText(account.getIdentitycard());
        holder.txtPhone.setText(account.getPhonenumber());
        holder.txtUserName.setText(account.getUsername());
        Picasso.with(context).load(account.getPicture()).into(holder.roundImageviewManagerAccount);
        if(account.getIdloai()==0){
            holder.txtTypeAccount.setText("Khách Hàng");
        }
        if(account.getIdloai()==1){
            holder.txtTypeAccount.setText("Khách Hàng Thân Thiết");
        }
        if(account.getIdloai()==2){
            holder.txtTypeAccount.setText("Admin");
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class ViewholderManagerAccount extends RecyclerView.ViewHolder{
        private TextView txtAccountCode,txtUserName,txtFullName,txtEmail,txtPhone,txtIdentityCard,txtTypeAccount;
        private RoundedImageView roundImageviewManagerAccount;
        public ViewholderManagerAccount(@NonNull View itemView) {
            super(itemView);
            txtTypeAccount=itemView.findViewById(R.id.txtTypeAccount);
            txtUserName=itemView.findViewById(R.id.txtUserName);
            txtAccountCode=itemView.findViewById(R.id.txtAccountCode);
            txtFullName=itemView.findViewById(R.id.txtFullName);
            txtEmail=itemView.findViewById(R.id.txtEmail);
            txtPhone=itemView.findViewById(R.id.txtPhone);
            txtIdentityCard=itemView.findViewById(R.id.txtIdentityCard);
            roundImageviewManagerAccount=itemView.findViewById(R.id.roundImageviewManagerAccount);
        }
    }
}
