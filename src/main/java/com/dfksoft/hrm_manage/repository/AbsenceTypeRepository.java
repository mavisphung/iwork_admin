package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.AbsenceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceTypeRepository extends JpaRepository<AbsenceType, Integer> {
    AbsenceType findById(int id);
}
