package com.hanki.hanki.NavigationBar.MyReview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.hanki.hanki.R;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

public class MyReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        init();
    }

    public void init() {
        Toolbar myReview_toolbar = (Toolbar) findViewById(R.id.myReview_toolbar);
        myReview_toolbar.setTitle("마이 리뷰");
        setSupportActionBar(myReview_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView myReview_recyclerview = (RecyclerView) findViewById(R.id.myReview_recyclerview);
        myReview_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        MyReviewAdapter myReviewAdapter = new MyReviewAdapter(this, getMyReviewList());
        myReview_recyclerview.setAdapter(myReviewAdapter);
    }

    public ArrayList<MyReview> getMyReviewList() {
        ArrayList<MyReview> myReviewList = new ArrayList<>();
        MyReview myReview;
        for (int i = 0; i < 4; i++) {
            myReview = new MyReview("놀부 부대찌개", "김행키",
                    "https://pbs.twimg.com/profile_images/941882927725420545/9EvP7Y86_400x400.jpg",
                    3, "2018.11.09", "맛있당ㅎㅎㅎ");
            myReviewList.add(myReview);
        }
        return myReviewList;
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
