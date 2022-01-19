package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.Device;
import com.dfksoft.hrm_manage.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevice() {
        return (List<Device>) deviceRepository.findAll();
    }

    //add new account info
    public Device addNewDevice(String deviceName, String macAddress, int status) {
        Device device = new Device();

        device.setName(deviceName);
        device.setMacAddress(macAddress);
        device.setStatus(status);

        return deviceRepository.saveAndFlush(device);
    }

    public Device findByAccountIdAndStatus(int accountId, int status) {
        return deviceRepository.findByAccountIdAndStatus(accountId, status);
    }
    public Device setInactiveDevice(Device device) {
        device.setStatus(0);
        return deviceRepository.saveAndFlush(device);
    }

    public Device findDeviceById(int id) {
        return deviceRepository.findDeviceById(id);
    }

    public Device findDeviceByMacAddress(String macAddress){
        return deviceRepository.findDeviceByMacAddress(macAddress);
    }

    @Transactional
    public void deleteDeviceByAccountId(int id) {
        deviceRepository.deleteDeviceByAccountId(id);
    }

    public List<Device> getAllDeviceByAccountId(int accountId) {
        return (List<Device>) deviceRepository.findAllByAccountId(accountId);
    }

    //create device
    public Device initDevice(String name, String macAddress, int accountId, int status) {
        Device device = new Device();

        device.setName(name);
        device.setMacAddress(macAddress);
        device.setAccountId(accountId);
        device.setStatus(status);

        return deviceRepository.saveAndFlush(device);
    }

    // delete device
    public void deleteDevice(Integer id) {
        deviceRepository.deleteById(id);
    }

    // update device
    public  Device updateDevice(int id, String name, String macAddress, int status) {
        Device device = deviceRepository.findDeviceById(id);

        device.setName(name);
        device.setMacAddress(macAddress);
        device.setStatus(status);

        return deviceRepository.saveAndFlush(device);
    }
}
