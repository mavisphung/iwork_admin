package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.BackupLog;
import com.dfksoft.hrm_manage.repository.BackupLogRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BackupLogService {
    private final BackupLogRepository backupLogRepository;

    public BackupLogService(BackupLogRepository backupLogRepository) {
        this.backupLogRepository = backupLogRepository;
    }

    public List<BackupLog> getAllBackupLog() {
        return (List<BackupLog>) backupLogRepository.findAll();
    }

    @Transactional
    public void deleteBackupLogByDeviceId(int id) {
        backupLogRepository.deleteBackupLogByDeviceId(id);
    }

    // delete backup log
    public void deleteBackupLog(Integer id) {
        backupLogRepository.deleteById(id);
    }
}
