package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.ReportTemp;
import com.dfksoft.hrm_manage.repository.ReportTempRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReportTempService {
    private final ReportTempRepository reportTempRepository;

    public ReportTempService(ReportTempRepository reportTempRepository) {
        this.reportTempRepository = reportTempRepository;
    }

    public List<ReportTemp> getAllReportTemp() {
        return (List<ReportTemp>) reportTempRepository.findAll();
    }

    public String getAllTimeWorkOfReportTempByMacAddressAndDateBetween(String macAddress, LocalTime startDate, LocalTime endDate) {
        List<ReportTemp> reportTemps = reportTempRepository.findAllByMacAddressAndDateWorkBetween(macAddress, startDate, endDate);
        String timeWork = "timeWork";

        long total = sumAllTime(reportTemps, timeWork);

        return convertTimeToString(total);
    }

    public long getAllTimeWorkOfReportTempByMacAddress(String macAddress) {
        List<ReportTemp> reportTemps = reportTempRepository.findAllByMacAddress(macAddress);
        String timeWork = "timeWork";

        return sumAllTime(reportTemps, timeWork);
    }

    public long getAllTimeRetirementOfReportTempByMacAddress(String macAddress) {
        List<ReportTemp> reportTemps = reportTempRepository.findAllByMacAddress(macAddress);
        String timeRetirement = "timeRetirement";

        return sumAllTime(reportTemps, timeRetirement);
    }

    public String convertTimeToString(long total) {
        int hrs = (int) TimeUnit.MILLISECONDS.toHours(total);
        int min = (int) TimeUnit.MILLISECONDS.toMinutes(total) % 60;
        int sec = (int) TimeUnit.MILLISECONDS.toSeconds(total) % 60;

        return String.format("%02d:%02d:%02d", hrs, min, sec);
    }

    public long sumAllTime(List<ReportTemp> reportTemps, String time) {
        long total = reportTemps.stream().mapToLong(report -> {
            long timeInMilliseconds = 0;
            if(time == "timeWork") {
                timeInMilliseconds = report.getTimeWork();
            } else if (time == "timeRetirement") {
                timeInMilliseconds = report.getTimeRetirement();
            }
            return timeInMilliseconds;
        }).sum();

        return total;
    }
}
