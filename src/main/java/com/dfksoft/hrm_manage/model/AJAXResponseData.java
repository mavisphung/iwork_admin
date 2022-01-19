package com.dfksoft.hrm_manage.model;

import com.dfksoft.hrm_manage.entity.Report;
import com.dfksoft.hrm_manage.entity.data.ReportData;

import java.util.List;

public class AJAXResponseData {
    private List<ReportData> reports;
    private String timeWorkBetween;
    private String timeRetirementBetween;

    public String getTimeRetirementBetween() {
        return timeRetirementBetween;
    }

    public void setTimeRetirementBetween(String timeRetirementBetween) {
        this.timeRetirementBetween = timeRetirementBetween;
    }

    public String getTimeWorkBetween() {
        return timeWorkBetween;
    }

    public void setTimeWorkBetween(String timeWorkBetween) {
        this.timeWorkBetween = timeWorkBetween;
    }

    public List<ReportData> getReports() {
        return reports;
    }

    public void setReports(List<ReportData> reports) {
        this.reports = reports;
    }
}
