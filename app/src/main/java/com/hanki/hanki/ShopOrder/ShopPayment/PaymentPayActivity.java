package com.hanki.hanki.ShopOrder.ShopPayment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hanki.hanki.R;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;


public class PaymentPayActivity extends AppCompatActivity {
    public static PaymentPayActivity mContext;
    Toolbar mToolbar;
    private RecyclerView mPayRecyclerView;
    private LinearLayoutManager mPayLinearLayoutManager;
    private PaymentPayListAdapter mPayListAdapter;
    ArrayList<PaymentPayListData> mPayListData;

    //넘겨줄 것
    int mLogoUrl = 0;
    String  mLogoName = "";
    int mLogoPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_pay);

        mContext = this;
        mLogoPosition = getIntent().getIntExtra("logoPosition", 0);
        init();
        setLogoInfo(mLogoPosition, mLogoUrl, mLogoName);
    }

    public void setLogoInfo(int logoPosition, int logoUrl, String logoName){
        mLogoPosition = logoPosition;
        mLogoUrl = logoUrl;
        mLogoName = logoName;
    }

    public void setUpToolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("매장 이름2"); // toolbar 제목
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void initRecyclerView(){
        mPayRecyclerView = (RecyclerView) findViewById(R.id.paymentPay_RecyclerView);
        mPayLinearLayoutManager = new LinearLayoutManager(this);
        mPayLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mPayRecyclerView.setLayoutManager(mPayLinearLayoutManager);
        mPayRecyclerView.setHasFixedSize(true);


        mPayListData = new ArrayList<>();
        mPayListData.add(new PaymentPayListData("카카오페이", R.drawable.logo_kakao));
        mPayListData.add(new PaymentPayListData("네이버페이", R.drawable.logo_naversymbol));
        mPayListData.add(new PaymentPayListData("페이코", 0));
        mPayListData.add(new PaymentPayListData("카드결제", 0 ));
        mPayListData.add(new PaymentPayListData("휴대폰결제", 0));



        mPayListAdapter = new PaymentPayListAdapter(this, mPayListData);
        mPayRecyclerView.setAdapter(mPayListAdapter);
    }


    public void init(){
        mToolbar = (Toolbar) findViewById(R.id.paymentPay_toolbar);
        setUpToolbar();

        initRecyclerView();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.paymentPay_choiceLayout:
                Intent intent = new Intent(this, PaymentActivity.class);
                intent.putExtra("logoPosition", mLogoPosition);
                intent.putExtra("logoUrl", mLogoUrl);
                intent.putExtra("logoName", mLogoName);
//                intent.putExtra("isFirst", mFirst);
                finish();
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
