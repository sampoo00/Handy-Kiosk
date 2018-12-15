package com.hanki.hanki.ShopOrder.ShopMenu;

import java.util.ArrayList;

public class ShopOrderData {
    //유저아이디, 매장 고유코드, FCM 토큰 값, 주문내역 총 금액
    public String userId;
    public String shopCode;
    public String FCMToken;
    public String amount;

    public ArrayList<ShopOrderListData> list;

    public ShopOrderData(String userId, String shopCode, String FCMToken, String amount, ArrayList<ShopOrderListData> list) {
        this.userId = userId;
        this.shopCode = shopCode;
        this.FCMToken = FCMToken;
        this.amount = amount;
        this.list = list;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getFCMToken() {
        return FCMToken;
    }

    public void setFCMToken(String FCMToken) {
        this.FCMToken = FCMToken;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ArrayList<ShopOrderListData> getList() {
        return list;
    }

    public void setList(ArrayList<ShopOrderListData> list) {
        this.list = list;
    }
}
