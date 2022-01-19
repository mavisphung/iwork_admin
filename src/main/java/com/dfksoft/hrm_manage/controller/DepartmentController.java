package com.dfksoft.hrm_manage.controller;

import com.dfksoft.hrm_manage.entity.Company;
import com.dfksoft.hrm_manage.entity.Location;
import com.dfksoft.hrm_manage.entity.SystemDevice;
import com.dfksoft.hrm_manage.service.CompanyService;
import com.dfksoft.hrm_manage.service.LocationService;
import com.dfksoft.hrm_manage.service.SystemDeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DepartmentController {
    private final LocationService locationService;
    private final CompanyService companyService;
    private final SystemDeviceService systemDeviceService;

    public DepartmentController(LocationService locationService, CompanyService companyService,
                                SystemDeviceService systemDeviceService) {
        this.locationService = locationService;
        this.companyService = companyService;
        this.systemDeviceService = systemDeviceService;
    }

    @RequestMapping("/department")
    public String index(Model model) {
        List<Location> locations = locationService.getAllLocation();
        List<Company> companies = companyService.getAllCompany();

        model.addAttribute("companies", companies);
        model.addAttribute("locations", locations);

        return "department";
    }

    @RequestMapping(value = "/addNewDepartment", method = RequestMethod.POST)
    public String addNewDepartment(@RequestParam("name") String name,
                                   @RequestParam("address") String address, @RequestParam("status") int status,
                                   @RequestParam("companyId") int companyId, Model model) {
        try {
            locationService.initLocation(name, address, status, companyId);
        } catch (Exception e) {
            e.getMessage();
        }

        return "redirect:department";
    }

    @RequestMapping(value = "/removeDepartment", method = RequestMethod.GET)
    public String removeDepartment(@RequestParam("id") int id, Model model) {
        systemDeviceService.removeAllSystemDeviceByLocationId(id);
        locationService.deleteDepartment(id);

        return "redirect:department";
    }

    @RequestMapping(value = "/departmentDetail", method = RequestMethod.GET)
    public String departmentDetail(@RequestParam("id") int id, Model model) {
        List<SystemDevice> systemDevices = systemDeviceService.getAllDeviceServiceByLocationId(id);
        Location location = locationService.getLocationById(id);

        model.addAttribute("location", location);
        model.addAttribute("systemDevices", systemDevices);

        return "department-detail";
    }

    @RequestMapping(value = "/updateDepartmentDetail", method = RequestMethod.POST)
    public String updateDepartmentDetail(@RequestParam("locationId") int locationId, @RequestParam("locationName") String locationName,
                                 @RequestParam("address") String address, @RequestParam("status") int status,
                                 Model model) {
        locationService.updateLocation(locationId, locationName, address, status);

        return "redirect:departmentDetail?id=" + locationId;
    }

}
