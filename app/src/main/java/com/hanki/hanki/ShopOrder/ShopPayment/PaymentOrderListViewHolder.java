package com.hanki.hanki.ShopOrder.ShopPayment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanki.hanki.R;

public class PaymentOrderListViewHolder extends RecyclerView.ViewHolder {
    ImageView VH_foodImage;
    TextView VH_menuName;
    TextView VH_menuSize;
    TextView VH_menuTopping;

    public PaymentOrderListViewHolder(View itemView) {
        super(itemView);

        VH_foodImage = (ImageView) itemView.findViewById(R.id.payment_MenuImg);
        VH_menuName = (TextView) itemView.findViewById(R.id.payment_menuName);
        VH_menuSize = (TextView) itemView.findViewById(R.id.payment_menuSize);
        VH_menuTopping = (TextView) itemView.findViewById(R.id.payment_toppings);
    }
}
