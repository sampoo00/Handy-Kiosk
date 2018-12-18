package com.hanki.hanki.ShopOrder.ShopReview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanki.hanki.R;

import java.util.ArrayList;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class ShopReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<ShopReviewData> shopReviewList;
    PrettyDialog deleteReviewDialog;

    public ShopReviewAdapter(Context context, ArrayList<ShopReviewData> reviewList) {
        this.context = context;
        this.shopReviewList = reviewList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ReviewViewHolder reviewViewHolder = (ReviewViewHolder) holder;
        Glide.with(context).load(shopReviewList.get(position).userImage).into(reviewViewHolder.review_profile);
        reviewViewHolder.review_nickname.setText(shopReviewList.get(position).userName);
        reviewViewHolder.review_rating.setRating(shopReviewList.get(position).userScore);
        reviewViewHolder.review_date.setText(shopReviewList.get(position).writeDate);
        reviewViewHolder.review_content.setText(shopReviewList.get(position).review);

        reviewViewHolder.review_modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        reviewViewHolder.review_deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteReviewDialog = new PrettyDialog(context);
                deleteReviewDialog.setMessage(context.getResources().getString(R.string.deleteReviewMessage))
                        .setIcon(R.drawable.pdlg_icon_info)
                        .setIconTint(R.color.colorPrimary)
                        .addButton("확인",
                                R.color.pdlg_color_white,
                                R.color.colorPrimary,
                                new PrettyDialogCallback() {
                                    @Override
                                    public void onClick() {
                                        deleteReviewDialog.dismiss();
                                    }
                                })
                        .setCanceledOnTouchOutside(false);
                deleteReviewDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopReviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        ImageView review_profile;
        TextView review_nickname;
        RatingBar review_rating;
        TextView review_date;
        TextView review_content;

        ImageButton review_modifyBtn;
        ImageButton review_deleteBtn;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            review_profile = (ImageView) itemView.findViewById(R.id.review_profile);
            review_nickname = (TextView) itemView.findViewById(R.id.review_nickname);
            review_rating = (RatingBar) itemView.findViewById(R.id.review_rating);
            review_date = (TextView) itemView.findViewById(R.id.review_date);
            review_content = (TextView) itemView.findViewById(R.id.review_content);

            review_modifyBtn = (ImageButton) itemView.findViewById(R.id.review_modifyBtn);
            review_deleteBtn = (ImageButton) itemView.findViewById(R.id.review_deleteBtn);
        }
    }
}
