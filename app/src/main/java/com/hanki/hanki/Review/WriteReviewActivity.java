package com.hanki.hanki.Review;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hanki.hanki.R;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        init();
    }

    public void init() {
        Toolbar review_toolbar = (Toolbar) findViewById(R.id.review_toolbar);
        setSupportActionBar(review_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView writeReview_date = (TextView) findViewById(R.id.writeReview_date);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        writeReview_date.setText(sdf.format(now));

        Button review_saveBtn = (Button) findViewById(R.id.review_saveBtn);
        review_saveBtn.setOnClickListener(new View.OnClickListener() { //확인 버튼 클릭 시
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
