package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    Integer deleteDeviceByAccountId(Integer accountId);

    List<Device> findAllByAccountId(Integer accountId);

    Device findDeviceById(Integer id);

    Device findDeviceByMacAddress(String macAddress);

    Device findByAccountIdAndStatus(int accountId, int status);
}
