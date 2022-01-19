package com.dfksoft.hrm_manage.controller;

import com.dfksoft.hrm_manage.entity.Account;
import com.dfksoft.hrm_manage.service.AccountInfoService;
import com.dfksoft.hrm_manage.service.AccountService;
import com.dfksoft.hrm_manage.service.RoleService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {
    private final AccountService accountService;
    private final AccountInfoService accountInfoService;
    private final RoleService roleService;

    public MainController(AccountService accountService, AccountInfoService accountInfoService, RoleService roleService) {
        this.accountService = accountService;
        this.accountInfoService = accountInfoService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/login")
    public String loginForm(HttpServletRequest request) {

        return "login";
    }
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        return "logout";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", "Incorrect email/password");
        return "login";
    }


    @GetMapping(value = "/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            //encrypt password
            String encryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            // create user
            accountService.initAccount(username, encryptPassword, 1, 1, 1, 1);
//
//            int accountId = account.getId();
//
//            accountInfoService.addNewAccountInfo("admin", "admin", "male", "DFK", "0987654321", accountId);

        } catch (Exception e) {
            return e.getMessage();
        }

        return "redirect:login";
    }
}

