package com.example.quanlyoto_doan.Service;

public class APIServices {
    private static String baseurl="http://192.168.1.2/cuahangquanlyoto/public/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(baseurl).create(DataService.class);
    }
}
