package com.hanki.hanki.ShopOrder.ShopMenu;

public class ShopRecMenuData {
    public String menuName;
    public int menuPirce;

    public ShopRecMenuData(String menuName, int menuPirce) {
        this.menuName = menuName;
        this.menuPirce = menuPirce;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPirce() {
        return menuPirce;
    }

    public void setMenuPirce(int menuPirce) {
        this.menuPirce = menuPirce;
    }
}
