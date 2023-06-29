package com.deercorp.blackcompany;

import lombok.Data;

@Data
public class PostReviewRequest {
    private String companyUuid;
    private Long rating;
    private String content;
    private String paymentLeadTime;
    private String region;
}