package com.dfksoft.hrm_manage.entity.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReportData implements Serializable {
    private int id;

    private String username;

    private LocalDate dateWork;

    private String timeWork;

    private String startTimeWork;

    private String timeRetirement;

    private String macAddress;
    private String label;

    public ReportData(String username, LocalDate dateWork, String timeWork, String startTimeWork, String timeRetirement, String macAddress) {
        this.username = username;
        this.dateWork = dateWork;
        this.timeWork = timeWork;
        this.startTimeWork = startTimeWork;
        this.timeRetirement = timeRetirement;
        this.macAddress = macAddress;
    }

    public ReportData(String username, String timeWork, String timeRetirement, String macAddress, String label) {
        this.username = username;
        this.timeWork = timeWork;
        this.timeRetirement = timeRetirement;
        this.macAddress = macAddress;
        this.label = label;
    }

    public ReportData(String timeWork, String timeRetirement, String label) {
        this.timeWork = timeWork;
        this.timeRetirement = timeRetirement;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateWork() {
        return dateWork;
    }

    public void setDateWork(LocalDate dateWork) {
        this.dateWork = dateWork;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(String timeWork) {
        this.timeWork = timeWork;
    }

    public String getStartTimeWork() {
        return startTimeWork;
    }

    public void setStartTimeWork(String startTimeWork) {
        this.startTimeWork = startTimeWork;
    }

    public String getTimeRetirement() {
        return timeRetirement;
    }

    public void setTimeRetirement(String timeRetirement) {
        this.timeRetirement = timeRetirement;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
