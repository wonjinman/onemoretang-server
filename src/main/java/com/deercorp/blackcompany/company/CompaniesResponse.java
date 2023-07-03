package com.deercorp.blackcompany.company;

import lombok.Data;

import java.util.List;

@Data
public class CompaniesResponse {
    private List<CompanyResponse> companies;

    public CompaniesResponse(List<CompanyResponse> companies) {
        this.companies = companies;
    }
}
