package com.dfksoft.hrm_manage.controller;

import com.dfksoft.hrm_manage.entity.Account;
import com.dfksoft.hrm_manage.entity.data.ReportData;
import com.dfksoft.hrm_manage.model.AJAXRequestData;
import com.dfksoft.hrm_manage.model.AJAXResponseData;
import com.dfksoft.hrm_manage.service.AccountService;
import com.dfksoft.hrm_manage.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;


@Controller
public class DashboardController {
    private final ReportService reportService;
    private final AccountService accountService;

    public DashboardController(ReportService reportService, AccountService accountService) {
        this.reportService = reportService;
        this.accountService = accountService;
    }

    // get all report
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String index(Model model) {
        String timeWorks = reportService.getAllTimeWork();
        String timeRetirements = reportService.getAllTimeRetirement();
        List<Account> accountList = accountService.findAll();

        model.addAttribute("timeWorks", timeWorks);
        model.addAttribute("timeRetirements", timeRetirements);
        model.addAttribute("accountList", accountList);

        return "dashboard";
    }

    @PostMapping(value = "/get-report-between-date")
    public ResponseEntity<?> getReportBetweenDate(@RequestBody AJAXRequestData requestData) {
        AJAXResponseData result = new AJAXResponseData();
        List<ReportData> reports = reportService.getReportWorkBetween(requestData.getStartDate(), requestData.getEndDate());
        String timeWorkBetween = reportService.getTimeWorkBetweenDate(requestData.getStartDate(), requestData.getEndDate());
        String timeRetirementBetween = reportService.getTimeRetirementBetweenDate(requestData.getStartDate(), requestData.getEndDate());

        result.setReports(reports);
        result.setTimeWorkBetween(timeWorkBetween);
        result.setTimeRetirementBetween(timeRetirementBetween);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/get-chart-data")
    public ResponseEntity<?> getChartData(@RequestParam("accountId") int accountId, @RequestParam("dataType") int dataType) {
        AJAXResponseData result = new AJAXResponseData();
        List<ReportData> data = reportService.findDataByDataTypeAndAccount(accountId, dataType);
        result.setReports(data);

        return ResponseEntity.ok(result);
    }

}
