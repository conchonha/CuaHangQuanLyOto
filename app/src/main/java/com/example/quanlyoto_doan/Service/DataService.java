package com.example.quanlyoto_doan.Service;

import com.example.quanlyoto_doan.Model.Accessory;
import com.example.quanlyoto_doan.Model.Account;
import com.example.quanlyoto_doan.Model.PutForService;
import com.example.quanlyoto_doan.Model.Service;
import com.example.quanlyoto_doan.Model.VerhicleInfomation;

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
    Call<List<VerhicleInfomation>>checkAcount(@Field("idAcount") String idAcount);

    @FormUrlEncoded
    @POST("model/vehicleInformation/RegisterVehicleInfomation")
    Call<String>RegisterVehicleInfomation(@Field("producer") String producer,
                                          @Field("type") String type,
                                          @Field("capacity") String capacity,
                                          @Field("contermet") String contermet,
                                          @Field("idacount") String idacount);

    @FormUrlEncoded
    @POST("model/vehicleInformation/UpdateVehicleInfomation")
    Call<String>UpdateVehicleInfomation(@Field("producer") String producer,
                                          @Field("type") String type,
                                          @Field("capacity") String capacity,
                                          @Field("contermet") String contermet,
                                          @Field("idcar") String idacount);

    @FormUrlEncoded
    @POST("model/services/getDataPutServices")
    Call<List<PutForService>>getDataPutServices(@Field("id") String idtaikhoan,
                                                @Field("loai") String loai);

    @GET("model/account/getData")
    Call<List<Account>>getDataManagerAccount();

    @GET("model/services/getDataServices")
    Call<List<Service>>getDataListServices();

    @FormUrlEncoded
    @POST("model/services/registerServices")
    Call<String>registerServices(@Field("putdate") String putdate,
                                 @Field("time") String time,
                                 @Field("speedometer") String speedometer,
                                 @Field("note") String note,
                                 @Field("type") String type,
                                 @Field("address") String address,
                                 @Field("idacount") String idacount);
}
