package com.example.quanlyoto_doan.Service;

import com.example.quanlyoto_doan.Model.Accessory;
import com.example.quanlyoto_doan.Model.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("model/accessories/getDataAccessories")
    Call<List<Accessory>>getDataAccessories();

    @GET("model/accessories/getDataAccessoriesAll")
    Call<List<Accessory>>getDataAccessoriesAll();

    @FormUrlEncoded
    @POST("model/account/postLogin")
    Call<List<Account>>getdataAccount(@Field("username") String username,
                                      @Field("password") String password);

    @FormUrlEncoded
    @POST("model/account/postRegister")
    Call<String>postRegister(@Field("fullName") String fullName,
                             @Field("userName") String userName,
                            @Field("passWord") String passWord,
                            @Field("email") String email,
                            @Field("phoneNumBer") String phoneNumBer,
                            @Field("identityCard") String identityCard,
                            @Field("idType") String idType);

    @FormUrlEncoded
    @POST("model/vehicleInformation/checkAcount")
    Call<String>checkAcount(@Field("idAcount") String idAcount);
}
