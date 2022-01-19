package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findById(int id);
}
