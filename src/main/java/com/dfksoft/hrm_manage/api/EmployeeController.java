package com.dfksoft.hrm_manage.api;

import java.util.List;

import com.dfksoft.hrm_manage.entity.Account;
import com.dfksoft.hrm_manage.entity.AccountInfo;
import com.dfksoft.hrm_manage.service.AccountInfoService;
import com.dfksoft.hrm_manage.service.AccountService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class EmployeeController {
    
    private final AccountService accountService;
    private final AccountInfoService infoService;

    public EmployeeController(AccountService accountService, AccountInfoService infoService) {
        this.accountService = accountService;
        this.infoService = infoService;
    }

    @PostMapping(path = { "user/detail/" })
    public List<AccountInfo> getAccounts(String mac_address) {

        // List<Account> results = accountService.findAll();
        // System.out.println(results.size());
        List<AccountInfo> details = infoService.findAccountInfoByMacAddress(mac_address);
        System.out.println(mac_address);
        return details;
    }
    

}
