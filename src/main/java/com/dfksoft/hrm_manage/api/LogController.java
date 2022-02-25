package com.dfksoft.hrm_manage.api;

import com.dfksoft.hrm_manage.service.BackupLogService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/log/")
public class LogController {
    
    private final BackupLogService logService;

    public LogController(BackupLogService logService) {
        this.logService = logService;
    }

    @PostMapping("devices")
    public ResponseEntity writeLog(String mac_address, String mac_device) {
        System.out.println("mac_address: " + mac_address + "\nmac_device: " + mac_device);
        System.out.println("writeLog invoked");
        return ResponseEntity.ok().build();
    }

}
