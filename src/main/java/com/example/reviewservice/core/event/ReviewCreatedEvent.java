package com.example.reviewservice.core.event;

import lombok.Data;

@Data
public class ReviewCreatedEvent {
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
    private boolean ban;
    private int range;
    private boolean delivery;
    private boolean pickUp;
    private String userId;

}
