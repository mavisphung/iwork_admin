package com.dfksoft.hrm_manage.controller;

import com.dfksoft.hrm_manage.entity.Location;
import com.dfksoft.hrm_manage.entity.SystemDevice;
import com.dfksoft.hrm_manage.service.LocationService;
import com.dfksoft.hrm_manage.service.SystemDeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SystemDeviceController {
    private final SystemDeviceService systemDeviceService;
    private final LocationService locationService;

    public SystemDeviceController(SystemDeviceService systemDeviceService, LocationService locationService) {
        this.systemDeviceService = systemDeviceService;
        this.locationService = locationService;
    }

    @RequestMapping("/sys-device")
    public String index(Model model) {
        List<SystemDevice> systemDevices = systemDeviceService.getAllSystemDevice();
        List<Location> locations = locationService.getAllLocation();
        model.addAttribute("systemDevices", systemDevices);
        model.addAttribute("locations", locations);

        return "sys-device";
    }

    @RequestMapping("/systemDeviceDetail")
    public String systemDeviceDetail(@RequestParam("id") int id, Model model) {
        SystemDevice systemDevice = systemDeviceService.findSystemDeviceById(id);

        model.addAttribute("systemDevice", systemDevice);

        return "system-device-detail";
    }

    @RequestMapping(value = "/updateSystemDevice", method = RequestMethod.POST)
    public String updateSystemDevice(@RequestParam("id") int id, @RequestParam("locationId") int locationId,
                                     @RequestParam("name") String name,
                                     @RequestParam("macAddress") String macAddress, @RequestParam("status") int status,
                                     Model model) {
        systemDeviceService.updateSystemDevice(id, name, macAddress, status);

        return "redirect:departmentDetail?id=" + locationId;
    }

    @RequestMapping("/addNewSystemDevice")
    public String addNewSystemDevice(@RequestParam("locationId") int locationId,
                                     @RequestParam("name") String name,
                                     @RequestParam("macAddress") String macAddress,
                                     @RequestParam("status") int status, Model model) {
        try {
            systemDeviceService.createNewSystemDevice(name, macAddress, locationId, status);
        } catch (Exception e) {
            e.getMessage();
        }

        return "redirect:departmentDetail?id=" + locationId;
    }

    @RequestMapping(value = "/removeSystemDevice", method = RequestMethod.GET)
    public String removeSystemDevice(@RequestParam("id") int id,
                                     Model model) {
        SystemDevice systemDevice = systemDeviceService.findSystemDeviceById(id);
        systemDeviceService.removeSystemDeviceById(id);

        return "redirect:departmentDetail?id=" + systemDevice.getLocationId();
    }
}
