package com.deercorp.blackcompany.review;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "company_review")
@NoArgsConstructor
public class CompanyReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String username;
    private String companyUuid;
    private String managerUuid;
    private Long rating;
    private String content;
    private String paymentLeadTime;
    private String region;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public CompanyReviewEntity(String username, String companyUuid, Long rating, String content, String paymentLeadTime, String region) {
        this.uuid = UUID.randomUUID().toString();
        this.username = username;
        this.companyUuid = companyUuid;
        this.rating = rating;
        this.content = content;
        this.paymentLeadTime = paymentLeadTime;
        this.region = region;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
