package com.dfksoft.hrm_manage.controller;

import com.dfksoft.hrm_manage.entity.Role;
import com.dfksoft.hrm_manage.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("/role")
    public String index(Model model) {
        List<Role> roles = roleService.getAllRole();

        model.addAttribute("roles", roles);

        return "role";
    }

    @RequestMapping("/addNewRole")
    public String registerRole(@RequestParam("roleName") String roleName, @RequestParam("description") String description) {
        try {

            roleService.createRole(roleName, description);

        } catch (Exception e) {
            return e.getMessage();
        }

        return "redirect:role";
    }

    @RequestMapping(value = "/roleDetail", method = RequestMethod.GET)
    public String roleDetail(@RequestParam("id") int id, Model model) {
        // get account id
        Role role = roleService.findById(id);

        model.addAttribute("role", role);

        return "role-detail";
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public String updateRole(@RequestParam("id") int id, @RequestParam("roleName") String roleName, @RequestParam("description") String description,
                                 Model model) {
        roleService.updateRole(id, roleName, description);

        return "redirect:role";
    }

}
