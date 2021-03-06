package com.hanki.hanki.ShopOrder.NetworkItem;

public class ToppingData {
    String shopCode;
    String toppingGroupName;
    String toppingName;
    int toppingPrice;
    String toppingSoldOutYn;
    int toppingNum;

    public ToppingData(String shopCode, String toppingGroupName, String toppingName, int toppingPrice, String toppingSoldOutYn, int toppingNum) {
        this.shopCode = shopCode;
        this.toppingGroupName = toppingGroupName;
        this.toppingName = toppingName;
        this.toppingPrice = toppingPrice;
        this.toppingSoldOutYn = toppingSoldOutYn;
        this.toppingNum = toppingNum;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getToppingGroupName() {
        return toppingGroupName;
    }

    public void setToppingGroupName(String toppingGroupName) {
        this.toppingGroupName = toppingGroupName;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public int getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(int toppingPrice) {
        this.toppingPrice = toppingPrice;
    }

    public String getToppingSoldOutYn() {
        return toppingSoldOutYn;
    }

    public void setToppingSoldOutYn(String toppingSoldOutYn) {
        this.toppingSoldOutYn = toppingSoldOutYn;
    }

    public void setToppingNum(int toppingNum) {
        this.toppingNum = toppingNum;
    }

    public int getToppingNum() {
        return toppingNum;
    }
}
