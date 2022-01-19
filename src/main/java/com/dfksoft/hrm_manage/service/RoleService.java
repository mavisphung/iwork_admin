package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.Role;
import com.dfksoft.hrm_manage.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRole() {
        return (List<Role>) roleRepository.findAll();
    }
    public Role findById(int id) {
        return roleRepository.findById(id);
    }

    public Role createRole(String roleName, String description) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        return roleRepository.save(role);
    }

    public Role updateRole(int id, String roleName, String description) {
        Role role = roleRepository.findById(id);
        if (role != null) {
            role.setRoleName(roleName);
            role.setDescription(description);
            return roleRepository.saveAndFlush(role);
        } else {
            return role;
        }
    }
}
