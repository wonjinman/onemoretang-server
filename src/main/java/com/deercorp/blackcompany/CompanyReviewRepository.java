package com.deercorp.blackcompany;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyReviewRepository extends JpaRepository<CompanyReviewEntity, Long> {
    List<CompanyReviewEntity> findAllByCompanyUuid(String companyUuid);
}
