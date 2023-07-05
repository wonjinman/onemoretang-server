package com.deercorp.blackcompany.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    List<CompanyEntity> findTop10ByNameContaining(String query);

    Optional<CompanyEntity> findByUuid(String companyUuid);

}
