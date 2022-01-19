package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.SystemDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemDeviceRepository extends JpaRepository<SystemDevice, Integer> {
    List<SystemDevice> findAllByLocationId(Integer locationId);

    SystemDevice findSystemDeviceById(Integer id);
}
