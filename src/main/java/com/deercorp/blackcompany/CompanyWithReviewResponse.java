package com.deercorp.blackcompany;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompanyWithReviewResponse extends CompanyResponse {
    private List<CompanyReviewResponse> reviews;

    public CompanyWithReviewResponse(CompanyResponse companyResponse, List<CompanyReviewResponse> reviews) {
        super(companyResponse.getUuid(), companyResponse.getName(), companyResponse.getAddress(),
                companyResponse.getRepresentativeName(), companyResponse.getContact(), companyResponse.getSubContact(),
                companyResponse.getRegion(), companyResponse.getTotalReviews(), companyResponse.getAvgRating());
        this.reviews = reviews;
    }

}
