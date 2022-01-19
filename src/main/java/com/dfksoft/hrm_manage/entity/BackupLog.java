package com.dfksoft.hrm_manage.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "backup_log")
public class BackupLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "time_event")
    private Timestamp timeEvent;

    @Column(name = "log_name")
    private String logName;

    @Column(name = "status")
    private int status;

    @Column(name = "device_id")
    private int deviceId;

    public BackupLog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(Timestamp timeEvent) {
        this.timeEvent = timeEvent;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
}
