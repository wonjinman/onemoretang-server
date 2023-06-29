package com.deercorp.blackcompany;

import com.deercorp.blackcompany.common.CommonResponse;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final CompanyReviewRepository companyReviewRepository;

    public CompanyController(CompanyRepository companyRepository, CompanyReviewRepository companyReviewRepository) {
        this.companyRepository = companyRepository;
        this.companyReviewRepository = companyReviewRepository;
    }

    @GetMapping("/companies")
    public CommonResponse<CompaniesResponse> getAllCompanies(@RequestParam("query") String query) {
        List<CompanyEntity> companyEntityList = companyRepository.findFirst10ByName(query);

        // 회사 리뷰 가져오기
        List<CompanyResponse> companyResponses = companyEntityList.stream()
                .map(entity -> {
                    List<CompanyReviewEntity> companyReviewEntityList = companyReviewRepository.findCompanyReviewByCompanyUuid(entity.getUuid());
                    double avgRating = calculateAvgRating(companyReviewEntityList);
                    return new CompanyResponse(
                            entity.getUuid(),
                            entity.getName(),
                            entity.getAddress(),
                            entity.getRepresentativeName(),
                            entity.getContact(),
                            entity.getSubContact(),
                            entity.getRegion(),
                            (long) companyReviewEntityList.size(),
                            avgRating
                    );
                }).toList();


        return CommonResponse.ok(new CompaniesResponse(
                companyResponses
        ));
    }

    @GetMapping("/companies/{companyUuid}")
    public CommonResponse<CompanyWithReviewResponse> getCompanyById(@PathVariable String companyUuid) {
        CompanyEntity companyEntity = companyRepository.findByUuid(companyUuid);
        List<CompanyReviewEntity> companyReviewEntityList = companyReviewRepository.findCompanyReviewByCompanyUuid(companyUuid);
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


        double avgRating = calculateAvgRating(companyReviewEntityList);

        return CommonResponse.ok(
                new CompanyWithReviewResponse(
                        new CompanyResponse(
                                companyEntity.getUuid(),
                                companyEntity.getName(),
                                companyEntity.getAddress(),
                                companyEntity.getRepresentativeName(),
                                companyEntity.getContact(),
                                companyEntity.getSubContact(),
                                companyEntity.getRegion(),
                                (long) companyReviewEntityList.size(),
                                avgRating
                        ),
                        companyReviewResponseList
                )
        );
    }

    @GetMapping("/reviews/{companyUuid}")
    public List<CompanyReviewEntity> getReviewsById(@PathVariable String companyUuid) {
        return companyReviewRepository.findCompanyReviewByCompanyUuid(companyUuid);
    }

    @PostMapping("/reviews")
    public void postReview(@RequestBody PostReviewRequest postReviewRequest) {
        CompanyReviewEntity entity = new CompanyReviewEntity(
                "홍길동",
                postReviewRequest.getCompanyUuid(),
                postReviewRequest.getRating(),
                postReviewRequest.getContent(),
                postReviewRequest.getPaymentLeadTime(),
                postReviewRequest.getRegion()
        );

        companyReviewRepository.save(entity);
    }

    private double calculateAvgRating(List<CompanyReviewEntity> companyReviewEntityList) {
        double avgRating = 0.0;
        if (companyReviewEntityList.size() > 0) {
            avgRating = companyReviewEntityList.stream().mapToDouble(CompanyReviewEntity::getRating).sum() / companyReviewEntityList.size();
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            avgRating = Double.parseDouble(decimalFormat.format(avgRating));

        }
        return avgRating;
    }

}
