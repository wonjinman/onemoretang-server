package com.deercorp.blackcompany.company;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyResponse {
    private String uuid;
    private String name;
    private String address;
    private String representativeName;
    private String contact;
    private String subContact;
    private String region;
    private Long totalReviews;
    private double avgRating;

    public CompanyResponse(String uuid, String name, String address, String representativeName, String contact, String subContact, String region, Long totalReviews, double avgRating) {
        this.uuid = uuid;
        this.name = name;
        this.address = address;
        this.representativeName = representativeName;
        this.contact = contact;
        this.subContact = subContact;
        this.region = region;
        this.totalReviews = totalReviews;
        this.avgRating = avgRating;
    }
}
