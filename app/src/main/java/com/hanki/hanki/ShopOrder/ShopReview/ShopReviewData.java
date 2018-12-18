package com.hanki.hanki.ShopOrder.ShopReview;

public class ShopReviewData {
    String userName;
    String userImage;
    int userScore;
    String writeDate;
    String review;

    public ShopReviewData(String userName, String userImage, int userScore, String writeDate, String review) {
        this.userName = userName;
        this.userImage = userImage;
        this.userScore = userScore;
        this.writeDate = writeDate;
        this.review = review;
    }
}
