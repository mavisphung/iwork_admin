package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.SystemDevice;
import com.dfksoft.hrm_manage.repository.SystemDeviceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SystemDeviceService {
    private final SystemDeviceRepository systemDeviceRepository;

    public SystemDeviceService(SystemDeviceRepository systemDeviceRepository) {
        this.systemDeviceRepository = systemDeviceRepository;
    }

    public List<SystemDevice> getAllSystemDevice() {
        return (List<SystemDevice>) systemDeviceRepository.findAll();
    }

    public List<SystemDevice> getAllDeviceServiceByLocationId(int locationId) {
        return systemDeviceRepository.findAllByLocationId(locationId);
    }

    public SystemDevice findSystemDeviceById(int id) {
        return systemDeviceRepository.findSystemDeviceById(id);
    }

    // update device
    public SystemDevice updateSystemDevice(int id, String name, String macAddress, int status) {
        SystemDevice systemDevice = systemDeviceRepository.findSystemDeviceById(id);

        systemDevice.setName(name);
        systemDevice.setMacAddress(macAddress);
        systemDevice.setStatus(status);

        return systemDeviceRepository.saveAndFlush(systemDevice);
    }

    public SystemDevice createNewSystemDevice(String name, String macAddress, int locationId, int status) {
        SystemDevice systemDevice = new SystemDevice();

        systemDevice.setName(name);
        systemDevice.setMacAddress(macAddress);
        systemDevice.setLocationId(locationId);
        systemDevice.setStatus(status);

        return systemDeviceRepository.saveAndFlush(systemDevice);
    }

    public void removeAllSystemDeviceByLocationId(int locationId) {
        List<SystemDevice> systemDevices = systemDeviceRepository.findAllByLocationId(locationId);
        for (int i = 0; i < systemDevices.size(); i++) {
            systemDeviceRepository.delete(systemDevices.get(i));
        }
    }

    public void removeSystemDeviceById(int id) {
        systemDeviceRepository.deleteById(id);
    }
}
