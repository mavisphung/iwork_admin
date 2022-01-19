package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location getLocationById(int id);
}
