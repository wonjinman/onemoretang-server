package com.deercorp.blackcompany;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyReviewResponse {
    private String uuid;
    private String content;
    private Long rating;
    private LocalDateTime createdAt;
    private String paymentLeadTime;
    private String region;

    public CompanyReviewResponse(String uuid, String content, Long rating, LocalDateTime createdAt, String paymentLeadTime, String region) {
        this.uuid = uuid;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
        this.paymentLeadTime = paymentLeadTime;
        this.region = region;
    }
}
