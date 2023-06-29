package com.deercorp.blackcompany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    @Query(value = "SELECT * FROM company where company.name like %:query% LIMIT 10", nativeQuery = true)
    List<CompanyEntity> findFirst10ByName(@Param("query") String query);

    @Query(value = "SELECT * FROM company where company.uuid like :uuid", nativeQuery = true)
    CompanyEntity findByUuid(@Param("uuid") String uuid);

}
