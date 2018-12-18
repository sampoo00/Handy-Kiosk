package com.hanki.hanki.ShopOrder.ShopReview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanki.hanki.R;
import com.hanki.hanki.Review.WriteReviewActivity;

import java.util.ArrayList;

public class Fragment_reviews extends Fragment implements View.OnClickListener {

    RecyclerView reviewListRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_main_fragment_reviews, container, false);
        reviewListRecyclerView = (RecyclerView) view.findViewById(R.id.reviewListRecyclerView);

        Button writeReviewBtn = (Button) view.findViewById(R.id.writeReviewBtn);
        Button showMoreReviewBtn = (Button)view.findViewById(R.id.showMoreReviewBtn);
        writeReviewBtn.setOnClickListener(this);
        showMoreReviewBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<ShopReviewData> shopReviewList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            shopReviewList.add(new ShopReviewData("김행키",
                    "data:image/png;base64,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",
                    3,
                    "2018.11.10",
                    "맛있어요. 그런데 햄이 조금 더 많았으면 좋겠어요ㅎㅎ"));
        }

        reviewListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ShopReviewAdapter shopReviewAdapter = new ShopReviewAdapter(getContext(), shopReviewList);
        reviewListRecyclerView.setAdapter(shopReviewAdapter);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.writeReviewBtn: //작성 버튼
                Intent intent = new Intent(getActivity(), WriteReviewActivity.class);
                startActivity(intent);
                break;
            case R.id.showMoreReviewBtn: //더보기 버튼
                break;
        }
    }
}
