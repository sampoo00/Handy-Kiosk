package com.hanki.hanki.ShopOrder.ShopPayment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanki.hanki.ShopOrder.ShopPayment.IamPort.iamPortMainActivity;


import com.hanki.hanki.R;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.Arrays;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    Toolbar mToolbar;

    //메뉴 리스트
    private RecyclerView mOrderRecyclerView;
    private LinearLayoutManager mOrderLinearLayoutManager;
    private PaymentOrderListAdapter mPaymentAdapter;
    List<PaymentOrderListData> mOrderList;

    //결제하기
    RelativeLayout mToOrderLayout;

    //메뉴 정보 확인
    TextView mFirstPriceText;
    Button mChangePayBtn;

    //결제 정보
    RelativeLayout mPaymentPayLayout;
    int mLogoImageUrl;
    String mInitLogoName;
    int mLogoPostion;
    ImageView mLogoImage;
    TextView mLogoName;

    //쿠폰, 포인트, 캐쉬백
    LinearLayout mCouponLayout;
    LinearLayout mHankiPointLayout;
    LinearLayout mOKCashBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        init();
        setupToolbar();
        mLogoImageUrl = getIntent().getIntExtra("logoUrl", R.drawable.logo_kakao);
        mInitLogoName = getIntent().getStringExtra("logoName");
        mLogoPostion = getIntent().getIntExtra("logoPosition", 0);
        if(mInitLogoName == null){
            mInitLogoName = "카카오페이";
        }
        else{
            mOKCashBackLayout.requestFocus();
        }

        setPaymentInfo(mLogoImageUrl, mInitLogoName);


    }

    public void init(){
        mToolbar = (Toolbar) findViewById(R.id.payment_toolbar);

        initRecyclerView();

        mToOrderLayout = (RelativeLayout) findViewById(R.id.payment_toOrderLayout);

        //결제 정보
        mFirstPriceText = (TextView) findViewById(R.id.payment_PriceTxt);
        mPaymentPayLayout = (RelativeLayout) findViewById(R.id.payment_payLayout);
        mChangePayBtn = (Button) findViewById(R.id.payment_changePayBtn);
        mLogoImage = (ImageView) findViewById(R.id.payment_payLogo);
        mLogoName = (TextView) findViewById(R.id.payment_payTxt);

        //쿠폰, 포인트, 캐시백
        mCouponLayout = (LinearLayout) findViewById(R.id.payment_couponLayout);
        mHankiPointLayout = (LinearLayout) findViewById(R.id.payment_pointLayout);
        mOKCashBackLayout = (LinearLayout) findViewById(R.id.payment_okCashBackLayout);
        mOKCashBackLayout.setFocusableInTouchMode(true);

    }

    public void setPaymentInfo(int imageUrl, String imageName){
        if(imageUrl == 0){
            mLogoImage.setVisibility(View.GONE);
            mLogoName.setText(imageName);
        }else{
            mLogoImage.setVisibility(View.VISIBLE);
            mLogoImage.setBackgroundResource(imageUrl);
            mLogoName.setText(imageName);

        }
    }

    public void initRecyclerView(){
        mOrderRecyclerView = (RecyclerView) findViewById(R.id.payment_orderListRecyclerView);
        mOrderLinearLayoutManager = new LinearLayoutManager(this);
        mOrderLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mOrderRecyclerView.setLayoutManager(mOrderLinearLayoutManager);
        mOrderRecyclerView.setHasFixedSize(true);

        initList();

        mPaymentAdapter = new PaymentOrderListAdapter(this, mOrderList);
        mOrderRecyclerView.setAdapter(mPaymentAdapter);
    }

    public void initList(){

        mOrderList = Arrays.asList(new PaymentOrderListData("케이크", "중",
                new String[]{"딸기", "라즈베리"}),
                new PaymentOrderListData("케이크2", "소",
                        new String[]{"딸기2", "라즈베리2"}),
                new PaymentOrderListData("케이크3", "대",
                        new String[]{"딸기3", "라즈베리3"}),
                new PaymentOrderListData("케이크4", "대",
                        new String[]{"딸기4", "라즈베리4"}),
                new PaymentOrderListData("케이크4-1", "대",
                        new String[]{"딸기4-1", "라즈베리4-1"}),
                new PaymentOrderListData("케이크5", "대",
                        new String[]{"딸기5", "라즈베리5", "라즈베리5", "라즈베리5", "라즈베리5", "라즈베리5"}));



    }
    public void setupToolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("매장 이름"); // toolbar 제목
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.payment_changePayBtn:
//                Toast.makeText(getApplicationContext(), "변경Layout만들기", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, PaymentPayActivity.class);
                intent.putExtra("logoPosition", mLogoPostion);
                startActivity(intent);
                break;

            case R.id.payment_toOrderLayout: // 결제하기 Layout
//                Toast.makeText(getApplicationContext(), "결제하기", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, iamPortMainActivity.class);
                startActivity(intent);
                break;
            case R.id.payment_couponLayout: //쿠폰 Layout
                Toast.makeText(getApplicationContext(), "쿠폰", Toast.LENGTH_SHORT).show();
                break;
            case R.id.payment_pointLayout: //포인트 Layout
                Toast.makeText(getApplicationContext(), "행키 포인트", Toast.LENGTH_SHORT).show();
                break;
            case R.id.payment_okCashBackLayout: //OKcashBack Layout
                Toast.makeText(getApplicationContext(), "오케이 캐쉬백", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
