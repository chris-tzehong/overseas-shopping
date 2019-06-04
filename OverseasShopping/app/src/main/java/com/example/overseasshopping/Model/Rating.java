package com.example.overseasshopping.Model;

public class Rating {

    private int ratingNo;
    private int rating;
    private String ratedBy;

    public Rating() {

    }

    public Rating(int ratingNo, int rating, String ratedBy) {
        this.ratingNo = ratingNo;
        this.rating = rating;
        this.ratedBy = ratedBy;
    }

    public int getRatingNo() {
        return ratingNo;
    }

    public void setRatingNo(int ratingNo) {
        this.ratingNo = ratingNo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(String ratedBy) {
        this.ratedBy = ratedBy;
    }
}

