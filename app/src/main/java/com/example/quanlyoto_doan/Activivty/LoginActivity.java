package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quanlyoto_doan.Model.Account;
import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ImageButton btRegister;
    private Button btnRegisTration;
    private EditText edtUserName,edtPassWord;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo_gin);
        nextPageRegister();
        anhxa();
        RegisTraion();
    }

    private void RegisTraion() {
        btnRegisTration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUserName.getText().toString().equals("") || edtPassWord.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Not leave data blank", Toast.LENGTH_SHORT).show();
                }else {
                    DataService dataService= APIServices.getService();
                    Call<List<Account>>callback=dataService.getdataAccount(edtUserName.getText().toString(),
                            edtPassWord.getText().toString());
                    callback.enqueue(new Callback<List<Account>>() {
                        @Override
                        public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                            Log.d("AAA","postLoginAcount: "+response.toString());
                            if(response.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Succsess", Toast.LENGTH_SHORT).show();
                                ArrayList<Account>arrayList= (ArrayList<Account>) response.body();
                                if(arrayList.size()>0){
                                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                    editor.putString("cusTomer","Customer");
                                    editor.putString("fullName",arrayList.get(0).getFullname());
                                    editor.putString("userName",arrayList.get(0).getUsername());
                                    editor.putString("passWord",arrayList.get(0).getPassword());
                                    editor.putString("email",arrayList.get(0).getEmail());
                                    editor.putString("phoneNumBer",arrayList.get(0).getPhonenumber());
                                    editor.putString("identityCard",arrayList.get(0).getIdentitycard());
                                    editor.putString("picture",arrayList.get(0).getPicture());
                                    editor.commit();
                                }else{
                                    Toast.makeText(LoginActivity.this, "Not have data ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Account>> call, Throwable t) {
                            Log.d("AAA","erroLogin: "+t.toString());
                        }
                    });
                }
            }
        });
    }

    private void anhxa() {
        sharedPreferences=getSharedPreferences("dataLogin",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        if(!sharedPreferences.getString("cusTomer","").equals("")){
           startActivity(new Intent(getApplicationContext(),HomeActivity.class));
           finish();
        }
        edtPassWord=findViewById(R.id.edtPassWord);
        edtUserName=findViewById(R.id.edtUserName);
        btnRegisTration=findViewById(R.id.btnRegisTration);
    }

    private void nextPageRegister() {
        btRegister=findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}
