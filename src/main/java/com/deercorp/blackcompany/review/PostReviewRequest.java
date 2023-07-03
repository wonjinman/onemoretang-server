package com.deercorp.blackcompany.review;

import lombok.Data;

@Data
public class PostReviewRequest {
    private String companyUuid;
    private Long rating;
    private String content;
    private String paymentLeadTime;
    private String region;
}
