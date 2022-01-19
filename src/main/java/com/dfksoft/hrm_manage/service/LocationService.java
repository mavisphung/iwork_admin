package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.Location;
import com.dfksoft.hrm_manage.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocation() {
        return (List<Location>) locationRepository.findAll();
    }

    //create account
    public Location initLocation(String name, String address, int status, int companyId) {
        Location location = new Location();

        location.setName(name);
        location.setAddress(address);
        location.setStatus(status);
        location.setCompanyId(companyId);

        return locationRepository.saveAndFlush(location);
    }

    public void deleteDepartment(int id) {
        locationRepository.deleteById(id);
    }

    public Location getLocationById (int id) {
        return locationRepository.getLocationById(id);
    }

    public Location updateLocation(int id, String name, String address, int status) {
        Location location = locationRepository.getLocationById(id);

        location.setName(name);
        location.setAddress(address);
        location.setStatus(status);

        return locationRepository.saveAndFlush(location);
    }
}
