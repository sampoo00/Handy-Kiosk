package com.hanki.hanki.Util;

import com.tsengvn.typekit.Typekit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Application extends android.app.Application {

    private static Application instance;
    private NetworkService networkService;

    // **** url 변경 **** //
    public String baseUrl = "http://117.17.156.101:8180/"; //서버용 주소
    public String tempUrl = "http://192.168.1.108:8080/"; //로컬용 주소, 테스트용
    // ***************** //

    public String imageUrl = "http://117.17.156.101:8180/HANKI/image/"; //이미지 경로

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildNetworkService();
        Application.instance = this;

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "font/NanumSquareR.ttf"))
                .addBold(Typekit.createFromAsset(this, "font/NanumSquareB.ttf"));
    }

    public void buildNetworkService() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseUrl) //여기서 바꿔주기 (baseUrl or tempUrl), ShopLogoAdapter 이미지 URL도 바꿔주기
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        networkService = retrofit.create(NetworkService.class);
    }

    public NetworkService getNetworkService() {
        return networkService;
    }

}
