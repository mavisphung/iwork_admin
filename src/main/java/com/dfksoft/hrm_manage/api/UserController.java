package com.dfksoft.hrm_manage.api;

import java.util.ArrayList;
import java.util.List;

import com.dfksoft.hrm_manage.entity.Account;
import com.dfksoft.hrm_manage.entity.AccountInfo;
import com.dfksoft.hrm_manage.entity.Report;
import com.dfksoft.hrm_manage.model.ReportDto;
import com.dfksoft.hrm_manage.model.UserInfomationDto;
import com.dfksoft.hrm_manage.service.AccountInfoService;
import com.dfksoft.hrm_manage.service.AccountService;
import com.dfksoft.hrm_manage.service.ReportService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class UserController {
    
    private final AccountService accountService;
    private final AccountInfoService infoService;
    private final ReportService reportService;

    public UserController(AccountService accountService, AccountInfoService infoService, ReportService reportService) {
        this.accountService = accountService;
        this.infoService = infoService;
        this.reportService = reportService;
    }

    @PostMapping(path = { "user/detail" })
    public UserInfomationDto getAccounts(String mac_address) {

        // List<Account> results = accountService.findAll();
        // System.out.println(results.size());
        List<AccountInfo> details = infoService.findAccountInfoByMacAddress(mac_address);
        System.out.println(mac_address);
        UserInfomationDto dto = new UserInfomationDto();
        dto.setStatus(200);
        dto.setDataReport(details.get(0));
        return details.size() >= 0 ? dto : null;
    }
    
    @PostMapping(path = { "user/reports" })
    public ReportDto writeLog(String mac_address) {
        Report report = reportService.getReportByMacAddress(mac_address);
        ArrayList<Report> reports = new ArrayList<Report>();
        reports.add(report);

        System.out.println("mac_address: " + mac_address);
        System.out.println("Report: " + report);
        ReportDto dto = new ReportDto();
        dto.setStatus(200);
        dto.setDataReport(reports);
        System.out.println("size: " + dto.getDataReport().size());
        return dto;
    }
}
