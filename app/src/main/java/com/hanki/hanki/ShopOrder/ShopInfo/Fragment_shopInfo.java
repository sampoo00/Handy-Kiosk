package com.hanki.hanki.ShopOrder.ShopInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanki.hanki.R;
import com.hanki.hanki.Util.Application;
import com.hanki.hanki.Util.NetworkService;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_shopInfo extends Fragment {

    TextView shopInfo_runTime;
    TextView shopInfo_breakTime;
    TextView shopInfo_holiday;
    TextView shopInfo_callNum;
    TextView shopInfo_location;
    TextView shopInfo_ownerName;
    TextView shopInfo_businessName;
    TextView shopInfo_businessNum;
    RelativeLayout shopInfo_mapview;

    String uuid = null;

    public static final String TAG = "Fragment_shopInfo";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_main_fragment_shop_info, container, false);
        init(view);

        Bundle bundle = getArguments();
        uuid = bundle.getString("uuid");
        if(uuid != null) {
            shopInfoNetwork(); //여기에서 setInfoData(), setMapView() 호출
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopInfo_callNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + shopInfo_callNum.getText().toString()));
                startActivity(intent);
            }
        });
    }

    public void shopInfoNetwork() {
        NetworkService networkService = Application.getInstance().getNetworkService();

        Call<InfoResult> request = networkService.getShopInfoResult(uuid);
        request.enqueue(new Callback<InfoResult>() {
            @Override
            public void onResponse(Call<InfoResult> call, Response<InfoResult> response) {
                if(response.isSuccessful()) {
                    InfoResult infoResult = response.body();
                    if(infoResult != null) {
                        InfoData infoData = infoResult.result;
                        setInfoData(infoData);
                        setMapView(infoData);
                    }
                }
                Log.d(TAG, "매장정보 받아오기 성공");
            }

            @Override
            public void onFailure(Call<InfoResult> call, Throwable t) {
                Log.d(TAG, "매장정보 받아오기 실패 : " + t.getMessage());
            }
        });
    }

    public void setInfoData(InfoData infoData) {
        shopInfo_runTime.setText(infoData.getRunTime());
        shopInfo_breakTime.setText(infoData.getBreakTime());
        shopInfo_holiday.setText(infoData.getHoliday());
        shopInfo_location.setText(infoData.getAddr() + " " + infoData.getDetailAddr());
        shopInfo_ownerName.setText(infoData.getOwnerName());
        shopInfo_businessName.setText(infoData.getShopName());

        SpannableString content = new SpannableString(infoData.getShopCallNum());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        shopInfo_callNum.setText(content);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setMapView(InfoData infoData) {
        MapView mapView = new MapView(getActivity());
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(infoData.getPointY(), infoData.getPointX()), true); //지도 중심점 변경

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(infoData.getShopName());
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(infoData.getPointY(), infoData.getPointX()));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker);
        shopInfo_mapview.addView(mapView);
    }

    public void init(View view) {
        shopInfo_runTime = (TextView) view.findViewById(R.id.shopInfo_runTime);
        shopInfo_breakTime = (TextView) view.findViewById(R.id.shopInfo_breakTime);
        shopInfo_holiday = (TextView) view.findViewById(R.id.shopInfo_holiday);
        shopInfo_callNum = (TextView) view.findViewById(R.id.shopInfo_callNum);
        shopInfo_mapview = (RelativeLayout) view.findViewById(R.id.shopInfo_mapview);
        shopInfo_location = (TextView) view.findViewById(R.id.shopInfo_location);
        shopInfo_ownerName = (TextView) view.findViewById(R.id.shopInfo_ownerName);
        shopInfo_businessName = (TextView) view.findViewById(R.id.shopInfo_businessName);
        shopInfo_businessNum = (TextView) view.findViewById(R.id.shopInfo_businessNum);
    }
}
