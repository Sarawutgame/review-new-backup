package com.example.reviewservice.query.rest;

import lombok.Data;

@Data
public class FindReviewById {
    private String reviewId;

    public FindReviewById(String reviewId){
        this.reviewId = reviewId;
    }
}
