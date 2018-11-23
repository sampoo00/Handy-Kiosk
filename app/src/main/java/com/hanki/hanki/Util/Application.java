package com.hanki.hanki.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Application extends android.app.Application {

    private static Application instance;
    private NetworkService networkService;
    private String baseUrl = "http://117.17.156.101:8180";
    public String tempUrl = "http://192.168.1.108:8080";

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildNetworkService();
        Application.instance = this;
    }

    public void buildNetworkService() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(tempUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        networkService = retrofit.create(NetworkService.class);
    }

    public NetworkService getNetworkService() {
        return networkService;
    }

}
