package com.example.quanlyoto_doan.Activivty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlyoto_doan.R;
import com.example.quanlyoto_doan.Service.APIServices;
import com.example.quanlyoto_doan.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtUserNameRegister,edtPassWordRegister,edtPhoneNumberRegister,edtEmailRegister,
            edtFullNameRegister,edtAdressRegister,edtIndentityCard;
    private Button btnSignupRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        signup();
    }

    private void signup() {
        btnSignupRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtIndentityCard.getText().toString().equals("") ||
                        edtUserNameRegister.getText().toString().equals("") ||
                        edtPassWordRegister.getText().toString().equals("") ||
                        edtPhoneNumberRegister.getText().toString().equals("") ||
                        edtEmailRegister.getText().toString().equals("") ||
                        edtFullNameRegister.getText().toString().equals("") ||
                        edtAdressRegister.getText().toString().equals("") ){

                    Toast.makeText(getApplicationContext(), "Not leave data blank", Toast.LENGTH_SHORT).show();
                }else if(!edtEmailRegister.getText().toString().endsWith("@gmail.com")){
                    Toast.makeText(getApplicationContext(), "Erro Email", Toast.LENGTH_SHORT).show();
                }else if(edtPhoneNumberRegister.getText().toString().length()!=10 ){
                    Toast.makeText(getApplicationContext(), "Erro phone number", Toast.LENGTH_SHORT).show();
                }else if(edtIndentityCard.getText().toString().length()!=9){
                    Toast.makeText(getApplicationContext(), "Erro IntentityCard", Toast.LENGTH_SHORT).show();
                }else{
                    DataService dataService= APIServices.getService();
                    Call<String>callback=dataService.postRegister(edtFullNameRegister.getText().toString(),
                            edtUserNameRegister.getText().toString(),
                            edtPassWordRegister.getText().toString(),
                            edtEmailRegister.getText().toString(),
                            edtPhoneNumberRegister.getText().toString(),
                            edtIndentityCard.getText().toString(),
                            "0");
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("AAA","postRegister: "+response.toString());
                            if(response.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }

    private void init() {
        edtAdressRegister=findViewById(R.id.edtAdressRegister);
        edtFullNameRegister=findViewById(R.id.edtFullNameRegister);
        edtEmailRegister=findViewById(R.id.edtEmailRegister);
        edtPhoneNumberRegister=findViewById(R.id.edtPhoneNumberRegister);
        edtPassWordRegister=findViewById(R.id.edtPassWordRegister);
        edtUserNameRegister=findViewById(R.id.edtUserNameRegister);
        btnSignupRegister=findViewById(R.id.btnSignupRegister);
        edtIndentityCard=findViewById(R.id.edtIndentityCard);
    }
}
