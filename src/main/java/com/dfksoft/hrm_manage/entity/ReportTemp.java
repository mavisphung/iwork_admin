package com.dfksoft.hrm_manage.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "report_temp")
public class ReportTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mac_address", nullable = false)
    private String macAddress;

    @Column(name = "count")
    private int count;

    @Column(name = "time_work")
    private int timeWork;

    @Column(name = "time_retirement")
    private int timeRetirement;

    @Column(name = "date_work")
    private LocalDate dateWork;

    @Column(name = "start_time_work")
    private int startTimeWork;

    public ReportTemp() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(int timeWork) {
        this.timeWork = timeWork;
    }

    public int getTimeRetirement() {
        return timeRetirement;
    }

    public void setTimeRetirement(int timeRetirement) {
        this.timeRetirement = timeRetirement;
    }

    public LocalDate getDateWork() {
        return dateWork;
    }

    public void setDateWork(LocalDate dateWork) {
        this.dateWork = dateWork;
    }

    public int getStartTimeWork() {
        return startTimeWork;
    }

    public void setStartTimeWork(int startTimeWork) {
        this.startTimeWork = startTimeWork;
    }
}
