package com.hanki.hanki.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.hanki.hanki.R;
import com.hanki.hanki.Util.Application;
import com.hanki.hanki.Util.NetworkService;
import com.minew.beacon.BluetoothState;
import com.minew.beacon.MinewBeacon;
import com.minew.beacon.MinewBeaconManager;
import com.minew.beacon.MinewBeaconManagerListener;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBeaconActivity extends AppCompatActivity {

    RecyclerView search_beacon_recyclerView;
    LottieAnimationView startSearchAniBtn;
    ShopLogoAdapter adapter;

    private MinewBeaconManager minewBeaconManager;
    private boolean isScanning;

    NetworkService networkService;
    ArrayList<ShopLogo> shopLogoList; //인식된 매장로고 리스트

    public static final String TAG = "SEARCH BEACON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_beacon);

        init();
    }

    public void init() {
        if (minewBeaconManager == null) {
            minewBeaconManager = MinewBeaconManager.getInstance(this);
        }
        minewBeaconManager.startScan();
        isScanning = true;

        networkService = Application.getInstance().getNetworkService();
        shopLogoList = new ArrayList<>();

        search_beacon_recyclerView = (RecyclerView) findViewById(R.id.search_beacon_recyclerView);
        search_beacon_recyclerView.setLayoutManager(new LinearLayoutManager(SearchBeaconActivity.this));
        adapter = new ShopLogoAdapter(SearchBeaconActivity.this, shopLogoList);
        search_beacon_recyclerView.setAdapter(adapter);

        startSearchAniBtn = (LottieAnimationView) findViewById(R.id.startSearchAniBtn);
        startSearchAniBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScan();
                startSearchAniBtn.setAnimation("json/motion.json");
                startSearchAniBtn.setRepeatCount(3);
                startSearchAniBtn.playAnimation();
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_beacon_closeBtn: //닫기 버튼
                finish();
                break;
        }
    }

    public void startScan() {
        //****** 매장명을 받아오기 위한 임시 코드 ****** //
        String UUID = "15290";
        Call<ShopLogo> request = networkService.getShopNameResult(UUID);
        request.enqueue(new Callback<ShopLogo>() {
            @Override
            public void onResponse(Call<ShopLogo> call, Response<ShopLogo> response) {
                if (response.isSuccessful()) {
                    if (shopLogoList.size() == 0) {
                        shopLogoList.add(response.body());
                    } else {
                        for (int i = 0; i < shopLogoList.size(); i++) {
                            if (!shopLogoList.get(i).getShopCode().equals(response.body().getShopCode())) {
                                shopLogoList.add(response.body());
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ShopLogo> call, Throwable t) {
                Log.d(TAG, "매장명 받아오기 FAIL " + t.getMessage());
            }
        });
        // *************************************** //

        //원래 코드
        minewBeaconManager.setDeviceManagerDelegateListener(new MinewBeaconManagerListener() {
            @Override
            public void onAppearBeacons(List<MinewBeacon> list) {
//                ShopLogo shopData;
//                for (int i = 0; i < list.size(); i++) {
//                    shopData = new ShopLogo(list.get(i).getBeaconValue(BeaconValueIndex.MinewBeaconValueIndex_Minor).getStringValue());
//                    if (shopData.getShopName().equals("15290") || shopData.getShopName().equals("15282")) {
//                        if (!shopNameList.contains(shopData)) {
//                            shopNameList.add(shopData);
//                        }
//                    }
//                }
            }

            @Override
            public void onDisappearBeacons(List<MinewBeacon> list) {

            }

            @Override
            public void onRangeBeacons(List<MinewBeacon> list) {

            }

            @Override
            public void onUpdateState(BluetoothState bluetoothState) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isScanning)
            minewBeaconManager.stopScan();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}

