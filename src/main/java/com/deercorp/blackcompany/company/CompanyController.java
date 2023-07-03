package com.deercorp.blackcompany.company;

import com.deercorp.blackcompany.common.CommonResponse;
import com.deercorp.blackcompany.review.PostReviewRequest;
import com.deercorp.blackcompany.review.CompanyReviewEntity;
import com.deercorp.blackcompany.review.CompanyReviewRepository;
import com.deercorp.blackcompany.review.CompanyReviewResponse;
import com.deercorp.blackcompany.review.CompanyWithReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final CompanyReviewRepository companyReviewRepository;

    @GetMapping("/companies")
    public CommonResponse<CompaniesResponse> getAllCompanies(@RequestParam("query") String query) {
        List<CompanyEntity> companyEntityList = companyRepository.findTop10ByNameContaining(query);
        // 회사 리뷰 가져오기
        List<CompanyResponse> companyResponses = companyEntityList.stream()
                .map(entity -> {
                    List<CompanyReviewEntity> companyReviews = companyReviewRepository.findAllByCompanyUuid(entity.getUuid());
                    return new CompanyResponse(
                            entity.getUuid(),
                            entity.getName(),
                            entity.getAddress(),
                            entity.getRepresentativeName(),
                            entity.getContact(),
                            entity.getSubContact(),
                            entity.getRegion(),
                            (long) companyReviews.size(),
                            getAverageRating(companyReviews)
                    );
                }).toList();
        return CommonResponse.ok(new CompaniesResponse(companyResponses));
    }

    @GetMapping("/companies/{companyUuid}")
    public CommonResponse<CompanyWithReviewResponse> getCompanyById(@PathVariable String companyUuid) {
        CompanyEntity companyEntity = companyRepository.findByUuid(companyUuid).orElseThrow(IllegalArgumentException::new);
        List<CompanyReviewEntity> companyReviewEntityList = companyReviewRepository.findAllByCompanyUuid(companyUuid);
        List<CompanyReviewResponse> companyReviewResponseList = companyReviewEntityList.stream()
                .map(entity ->
                        new CompanyReviewResponse(
                                entity.getUuid(),
                                entity.getContent(),
                                entity.getRating(),
                                entity.getCreatedAt(),
                                entity.getPaymentLeadTime(),
                                entity.getRegion()
                        )
                ).toList();

        return CommonResponse.ok(
                new CompanyWithReviewResponse(
                        companyEntity.getUuid(),
                        companyEntity.getName(),
                        companyEntity.getAddress(),
                        companyEntity.getRepresentativeName(),
                        companyEntity.getContact(),
                        companyEntity.getSubContact(),
                        companyEntity.getRegion(),
                        (long) companyReviewEntityList.size(),
                        getAverageRating(companyReviewEntityList),
                        companyReviewResponseList
                )
        );
    }

    @GetMapping("/reviews/{companyUuid}")
    public List<CompanyReviewEntity> getReviewsById(@PathVariable String companyUuid) {
        return companyReviewRepository.findAllByCompanyUuid(companyUuid);
    }

    @PostMapping("/reviews")
    public void postReview(@RequestHeader("x-device-key") String deviceKey, @RequestBody PostReviewRequest postReviewRequest) {
        CompanyReviewEntity entity = new CompanyReviewEntity(
                deviceKey,
                postReviewRequest.getCompanyUuid(),
                postReviewRequest.getRating(),
                postReviewRequest.getContent(),
                postReviewRequest.getPaymentLeadTime(),
                postReviewRequest.getRegion()
        );
        companyReviewRepository.save(entity);
    }

    private static double getAverageRating(List<CompanyReviewEntity> companyReviews) {
        return Math.floor(companyReviews.stream()
                .mapToDouble(CompanyReviewEntity::getRating)
                .average()
                .orElse(0.0) * 10) / 10;
    }
}
