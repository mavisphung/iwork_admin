package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
