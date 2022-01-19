package com.dfksoft.hrm_manage.controller;

import com.dfksoft.hrm_manage.entity.*;
import com.dfksoft.hrm_manage.service.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeesController {
    private final AccountService accountService;
    private final AccountInfoService accountInfoService;
    private final RoleService roleService;
    private final CompanyService companyService;
    private final DeviceService deviceService;
    private final TitleService titleService;
    private final ReportTempService reportTempService;
    private final ReportService reportService;

    public EmployeesController(AccountService accountService, AccountInfoService accountInfoService, RoleService roleService,
                               CompanyService companyService, DeviceService deviceService, TitleService titleService,
                               ReportTempService reportTempService, ReportService reportService) {
        this.accountService = accountService;
        this.accountInfoService = accountInfoService;
        this.roleService = roleService;
        this.companyService = companyService;
        this.deviceService = deviceService;
        this.titleService = titleService;
        this.reportTempService = reportTempService;
        this.reportService = reportService;
    }

    @RequestMapping("/employee")
    public String index(Model model) {
        List<AccountInfo> employee = accountInfoService.getAllAccountInfo();

        List<Role> role = roleService.getAllRole();

        List<Company> company = companyService.getAllCompany();

        List<Title> title = titleService.getAllTitle();

        model.addAttribute("employee", employee);
        model.addAttribute("role", role);
        model.addAttribute("company", company);
        model.addAttribute("title", title);

        return "employee";
    }

    @RequestMapping("/addNewEmployee")
    public String registerEmployee(@RequestParam("username") String username, @RequestParam("role") int role,
                                   @RequestParam("password") String password, @RequestParam("companyId") int companyId, @RequestParam("lastName") String lastName,
                                   @RequestParam("firstName") String firstName, @RequestParam("address") String address,
                                   @RequestParam("sex") String sex, @RequestParam("phone") String phone,
                                   @RequestParam("status") int status, @RequestParam("titleId") int titleId, Model model) {
        try {
            //encrypt password
            String encryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            // create user
            Account account = accountService.initAccount(username, encryptPassword, role, companyId, status, titleId);

            int accountId = account.getId();

            accountInfoService.addNewAccountInfo(firstName, lastName, sex, address, phone, accountId);

        } catch (Exception e) {
            return e.getMessage();
        }

        return "redirect:employee";
    }

    @RequestMapping(value = "/employeeDetail", method = RequestMethod.GET)
    public String employeeDetail(@RequestParam("id") int id, Model model, @ModelAttribute("isExisting") String isExisting) {
        Account account = accountService.findAccountById(id);

        AccountInfo accountInfo = accountInfoService.getAccountInfoByAccountId(id);

        List<Role> role = roleService.getAllRole();

        List<Company> company = companyService.getAllCompany();

        List<Title> title = titleService.getAllTitle();

        List<Device> devices = deviceService.getAllDeviceByAccountId(id);
        long timeWorkToday = 0;
        long timeRetirementToday = 0;
        for (int i = 0; i < devices.size(); i++) {
            String macAddress = devices.get(i).getMacAddress();
            long timeOfWork = reportTempService.getAllTimeWorkOfReportTempByMacAddress(macAddress);
            long timeOfRetirement = reportTempService.getAllTimeRetirementOfReportTempByMacAddress(macAddress);
            timeWorkToday += timeOfWork;
            timeRetirementToday += timeOfRetirement;
        }
        String convertTimeWorkToday = reportTempService.convertTimeToString(timeWorkToday);
        String convertTimeRetirementToday = reportTempService.convertTimeToString(timeRetirementToday);
        String convertTimeWorkALl = reportService.getAllTimeWorkByUsername(account.getUsername());
        String convertTimeRetirementAll = reportService.getALlTimeRetirementByUsername(account.getUsername());

        model.addAttribute("role", role);
        model.addAttribute("company", company);
        model.addAttribute("title", title);
        model.addAttribute("userDetail", account);
        model.addAttribute("devices", devices);
        model.addAttribute("accountInfo", accountInfo);
        model.addAttribute("timeWorkToday", convertTimeWorkToday);
        model.addAttribute("timeRetirementToday", convertTimeRetirementToday);
        model.addAttribute("timeWorkALl", convertTimeWorkALl);
        model.addAttribute("timeRetirementAll", convertTimeRetirementAll);
        model.addAttribute("isExisting", isExisting);

        return "employee-detail";
    }

    @RequestMapping(value = "/removeEmployee", method = RequestMethod.GET)
    public String removeEmployee(@RequestParam("id") int id, Model model) {
        accountInfoService.deleteAccountInfo(id);
        deviceService.deleteDeviceByAccountId(id);
        accountService.deleteAccount(id);

        return "redirect:employee";
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
    public String updateEmployee(@RequestParam("accountId") int accountId, @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName, @RequestParam("sex") String sex,
                                 @RequestParam("address") String address, @RequestParam("phone") String phone,
                                 @RequestParam("role") int roleId, @RequestParam("titleId") int titleId,
                                 @RequestParam("status") int status, @RequestParam("companyId") int companyId,
                                 Model model) {
        accountService.updateAccount(accountId, roleId, titleId, status, companyId);
        accountInfoService.updateAccountInfoByFindByAccountId(accountId, firstName, lastName, sex, address, phone);

        return "redirect:employee";
    }

}
