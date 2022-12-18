package com.example.reviewservice.command.rest;

import lombok.Data;

@Data
public class UpdateReviewModel {

    private String _id;
    private String name;
    private String branch;
    private String store_type;
    private String description;
    private String imageId;
    private String address;
    private String timeOpen;
    private String timeClose;
    private int rating;
    private int personReview;
    private String phone;
    private String report;
    private String ban;
}
