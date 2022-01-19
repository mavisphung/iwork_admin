package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.Company;
import com.dfksoft.hrm_manage.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompany() {
        return (List<Company>) companyRepository.findAll();
    }
}
