package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.AbsenceType;
import com.dfksoft.hrm_manage.repository.AbsenceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceTypeService {
    private final AbsenceTypeRepository absenceTypeRepository;

    public AbsenceTypeService(AbsenceTypeRepository absenceTypeRepository) {
        this.absenceTypeRepository = absenceTypeRepository;
    }

    public List<AbsenceType> findAll() {
        return absenceTypeRepository.findAll();
    }
    public AbsenceType findById(int id) {
        return absenceTypeRepository.findById(id);
    }

    public AbsenceType createAbsenceType(String desc, String appliedTo) {
        AbsenceType absenceType = new AbsenceType();
        absenceType.setDescription(desc);
        absenceType.setAppliedTo(appliedTo.charAt(0));
        return absenceTypeRepository.saveAndFlush(absenceType);
    }

    public AbsenceType updateAbsenceType(int id, String description, String appliedTo) {
        AbsenceType absenceType = absenceTypeRepository.findById(id);
        absenceType.setDescription(description);
        absenceType.setAppliedTo(appliedTo.charAt(0));
        return absenceTypeRepository.saveAndFlush(absenceType);
    }


}
