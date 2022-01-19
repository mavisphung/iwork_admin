package com.dfksoft.hrm_manage.controller;

import com.dfksoft.hrm_manage.entity.Device;
import com.dfksoft.hrm_manage.entity.Location;
import com.dfksoft.hrm_manage.service.BackupLogService;
import com.dfksoft.hrm_manage.service.DeviceService;
import com.dfksoft.hrm_manage.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class DeviceController {
    private final DeviceService deviceService;
    private final BackupLogService backupLogService;

    public DeviceController(DeviceService deviceService, BackupLogService backupLogService) {
        this.deviceService = deviceService;
        this.backupLogService = backupLogService;
    }

    @RequestMapping("/device")
    public String index(Model model) {
        List<Device> device = deviceService.getAllDevice();

        model.addAttribute("device", device);

        return "device";
    }

    @RequestMapping("/addNewDevice")
    public RedirectView addNewDevice(@RequestParam("accountId") int accountId, @RequestParam("name") String name,
                                     @RequestParam("macAddress") String macAddress,
                                     @RequestParam("status") int status, Model model, RedirectAttributes redirectAttributes) {
        try {
            Device device = deviceService.findDeviceByMacAddress(macAddress);
            if (device == null) {
                if (status == 1) {
                    Device device1 = deviceService.findByAccountIdAndStatus(accountId, status);
                    if (device1 != null) {
                        deviceService.setInactiveDevice(device1);
                    }
                }
                deviceService.initDevice(name, macAddress, accountId, status);
            } else {
                redirectAttributes.addFlashAttribute("isExisting", "Mac Address is existing!!!!");
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return new RedirectView("employeeDetail?id=" + accountId);
    }

    @RequestMapping(value = "/removeDevice", method = RequestMethod.GET)
    public String removeDevice(@RequestParam("id") int id, Model model) {
        // get account id
        Device device = deviceService.findDeviceById(id);

        backupLogService.deleteBackupLogByDeviceId(id);
        deviceService.deleteDevice(id);

        return "redirect:employeeDetail?id=" + device.getAccountId();
    }

    @RequestMapping(value = "/deviceDetail", method = RequestMethod.GET)
    public String deviceDetail(@RequestParam("id") int id, Model model) {
        // get account id
        Device device = deviceService.findDeviceById(id);

        model.addAttribute("device", device);

        return "device-detail";
    }

    @RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
    public String updateEmployee(@RequestParam("id") int id, @RequestParam("accountId") int accountId, @RequestParam("name") String name,
                                 @RequestParam("macAddress") String macAddress, @RequestParam("status") int status,
                                 Model model) {
        if (status == 1) {
            Device device1 = deviceService.findByAccountIdAndStatus(accountId, status);
            if (device1 != null) {
                deviceService.setInactiveDevice(device1);
            }
        }
        deviceService.updateDevice(id, name, macAddress, status);

        return "redirect:employeeDetail?id=" + accountId;
    }
}
