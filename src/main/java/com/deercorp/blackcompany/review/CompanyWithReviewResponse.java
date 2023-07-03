package com.deercorp.blackcompany.review;

import lombok.Data;

import java.util.List;

@Data
public class CompanyWithReviewResponse {
    private String uuid;
    private String name;
    private String address;
    private String representativeName;
    private String contact;
    private String subContact;
    private String region;
    private Long totalReviews;
    private double avgRating;
    private List<CompanyReviewResponse> reviews;

    public CompanyWithReviewResponse(String uuid, String name, String address, String representativeName, String contact, String subContact, String region, Long totalReviews, double avgRating, List<CompanyReviewResponse> reviews) {
        this.uuid = uuid;
        this.name = name;
        this.address = address;
        this.representativeName = representativeName;
        this.contact = contact;
        this.subContact = subContact;
        this.region = region;
        this.totalReviews = totalReviews;
        this.avgRating = avgRating;
        this.reviews = reviews;
    }
}
