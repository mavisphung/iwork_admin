package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.BackupLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupLogRepository extends JpaRepository<BackupLog, Integer> {
    Integer deleteBackupLogByDeviceId(Integer deviceId);
}
