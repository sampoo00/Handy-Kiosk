package com.hanki.hanki.NumberTicket;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanki.hanki.ShopOrder.NetworkItem.ShopResult;
import com.hanki.hanki.ShopOrder.NetworkItem.ShopTopInfo;
import com.hanki.hanki.Util.Application;
import com.hanki.hanki.Util.NetworkService;
import com.hanki.hanki.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_numberticket extends Fragment {
    //메뉴 정보
    TextView NumTicket_ShopName;
    ImageView NumTicket_menuImg;
    TextView NumTicket_menuName;
    TextView NumTicket_menuSize;
    TextView NumTicket_toppings;
    TextView NumTicket_sendMsg;

    //대기 순서
    TextView NumTicket_waitNum;

    //주문 정보
    TextView NumTicket_orderNum;
    TextView NumTicket_orderDate;
    TextView NumTicket_orderPrice;
    TextView NumTicket_OrderType; //신용카드, 후대폰 결제 등
    TextView NumTicket_point; //포인트


    NetworkService networkService;

    public final static String TAG = "NUMBER_TICKET";

    public Fragment_numberticket() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_numberticket, container, false);
//        testBtn = view.findViewById(R.id.testBtn);
//        testTv = view.findViewById(R.id.textTv);
        init(view);

        networkService = Application.getInstance().getNetworkService();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        testBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String UUID = "15290"; //UUID
//                String userId = ""; //userId 설정
//
//                //메뉴판 받아오기
//                Call<ShopResult> request = networkService.getShopMenuResult(UUID, userId);
//                request.enqueue(new Callback<ShopResult>() {
//                    @Override
//                    public void onResponse(Call<ShopResult> call, Response<ShopResult> response) {
//                        if (response.isSuccessful()) {
//                            ShopResult shopResult = response.body();
//                            ShopTopInfo shopTopInfo = shopResult.result;
//                            testTv.setText(shopTopInfo.toString());
//                            Log.d(TAG, "RESPONSE " + response.code());
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<ShopResult> call, Throwable t) {
//                        testTv.setText(t.getMessage());
//                        Log.d(TAG, "FAIL " + t.getMessage());
//                    }
//                });
//            }
//        });
    }

    public void init(View view){
        //메뉴 정보
        NumTicket_ShopName = (TextView) view.findViewById(R.id.NumTicket_ShopName);
        NumTicket_menuImg = (ImageView) view.findViewById(R.id.NumTicket_menuImg);
        NumTicket_menuName = (TextView) view.findViewById(R.id.NumTicket_menuName);
        NumTicket_menuSize = (TextView) view.findViewById(R.id.NumTicket_menuSize);
        NumTicket_toppings = (TextView) view.findViewById(R.id.NumTicket_toppings);
        NumTicket_sendMsg = (TextView) view.findViewById(R.id.NumTicket_sendMsg);

        //대기 순서
        NumTicket_waitNum = (TextView) view.findViewById(R.id.NumTicket_waitNum);

        //주문 정보
        NumTicket_orderNum = (TextView) view.findViewById(R.id.NumTicket_orderNum);
        NumTicket_orderDate = (TextView) view.findViewById(R.id.NumTicket_orderDate);
        NumTicket_orderPrice = (TextView) view.findViewById(R.id.NumTicket_orderPrice);
        NumTicket_OrderType = (TextView) view.findViewById(R.id.NumTicket_orderType); //신용카드, 후대폰 결제 등
        NumTicket_point = (TextView) view.findViewById(R.id.NumTicket_point); //포인트
    }
}
