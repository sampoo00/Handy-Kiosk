package com.hanki.hanki.Tutorial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.hanki.hanki.HomeActivity;
import com.hanki.hanki.R;
import com.tsengvn.typekit.TypekitContextWrapper;

public class TutorialActivity extends AppCompatActivity {
    ViewPager mViewPager;
    ViewPagerIndicator mViewPagerIndicator;
    int NUM_VIEWS = 3; //tutorial page 개수

    Button closeBtn;
    Button noshowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        init();
    }

    public void init() {
        //viewPager
        mViewPager = (ViewPager) findViewById(R.id.tutorial_viewpager);
        mViewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.tutorial_viewpager_indicator);

        mViewPager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(0); // 실행 시 0번째로 초기화
        mViewPagerIndicator.setupWithViewPager(mViewPager);
//        mViewPagerIndicator.addOnPageChangeListener(mOnPageChangeListener);

        //btn등록
        closeBtn = (Button) findViewById(R.id.tutorial_closeBtn);
        noshowBtn = (Button) findViewById(R.id.tutorial_noshowBtn);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tutorial_noshowBtn:
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("selectedNoShow", "yes").apply();
                break;
            case R.id.tutorial_closeBtn:
                break;
        }
        Intent intent = new Intent(TutorialActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    private class pagerAdapter extends FragmentStatePagerAdapter {

        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment_First();
                case 1:
                    return new Fragment_Second();
                case 2:
                    return new Fragment_Third();
                default:
                    return null;
            }
        }

        public int getCount() {
            return NUM_VIEWS;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
