package com.deercorp.blackcompany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CompanyReviewRepository extends JpaRepository<CompanyReviewEntity, Long> {
    @Query(value = "SELECT * from company_review where company_review.company_uuid like :companyUuid", nativeQuery = true)
    List<CompanyReviewEntity> findCompanyReviewByCompanyUuid(@Param("companyUuid")String companyUuid);
}
