package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.Account;
import com.dfksoft.hrm_manage.entity.Device;
import com.dfksoft.hrm_manage.entity.Report;
import com.dfksoft.hrm_manage.entity.ReportTemp;
import com.dfksoft.hrm_manage.entity.data.ReportData;
import com.dfksoft.hrm_manage.repository.AccountRepository;
import com.dfksoft.hrm_manage.repository.DeviceRepository;
import com.dfksoft.hrm_manage.repository.ReportRepository;
import com.dfksoft.hrm_manage.repository.ReportTempRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final ReportTempRepository reportTempRepository;
    private final AccountRepository accountRepository;
    private final DeviceRepository deviceRepository;

    public ReportService(ReportRepository reportRepository, ReportTempRepository reportTempRepository, AccountRepository accountRepository, DeviceRepository deviceRepository) {
        this.reportRepository = reportRepository;
        this.reportTempRepository = reportTempRepository;
        this.accountRepository = accountRepository;
        this.deviceRepository = deviceRepository;
    }

    public List<Report> getAllReport() {
        return (List<Report>) reportRepository.findAll();
    }

    public String getAllTimeWork() {
        List<Report> reports = (reportRepository.findAll());
        String timeWork = "timeWork";

        return convertTimeToString(reports, timeWork);
    }

    public List<ReportData> executeData( List<Report> reports, ReportTemp reportTemp) {
        List<ReportData> reportDataList = new ArrayList<>();

        if (reports != null) {
            for (int i = 0; i < reports.size(); i++) {
                LocalTime timeWork = LocalTime.ofSecondOfDay(Math.round(reports.get(i).getTimeWork() / 1000));
                LocalTime startTimeWork = LocalTime.ofSecondOfDay(Math.round(reports.get(i).getStartTimeWork() / 1000));
                LocalTime timeRetirement = LocalTime.ofSecondOfDay(Math.round(reports.get(i).getTimeRetirement() / 1000));
                ReportData reportData = new ReportData(reports.get(i).getUsername(), reports.get(i).getDateWork(), timeWork.toString(), startTimeWork.toString(), timeRetirement.toString(), reports.get(i).getMacAddress());
                reportData.setLabel(reports.get(i).getDateWork().getDayOfWeek().name());
                reportDataList.add(reportData);
            }
        }
        if (reportTemp != null) {
            LocalTime timeWork = LocalTime.ofSecondOfDay(Math.round(reportTemp.getTimeWork() / 1000));
            LocalTime startTimeWork = LocalTime.ofSecondOfDay(Math.round(reportTemp.getStartTimeWork() / 1000));
            LocalTime timeRetirement = LocalTime.ofSecondOfDay(Math.round(reportTemp.getTimeRetirement() / 1000));
            ReportData reportData = new ReportData(reportRepository.findByMacAddress(reportTemp.getMacAddress()).get(0).getUsername(), reportTemp.getDateWork(), timeWork.toString(), startTimeWork.toString(), timeRetirement.toString(), reportTemp.getMacAddress());
            reportData.setLabel(reportTemp.getDateWork().getDayOfWeek().name());
            reportDataList.add(reportData);
        }
        return reportDataList;
    }


    public List<ReportData> findDataByDataTypeAndAccount(int accountId, int dataType) {
        Account account = accountRepository.findAccountById(accountId);

        Device device = deviceRepository.findByAccountIdAndStatus(accountId, 1);

        if (device != null) {
            switch (dataType) {
                case 1:
                    // by present week
                    List<Report> reports1 = reportRepository.findDataInCurrentWeek(device.getMacAddress());
                    ReportTemp reportTemp1 = reportTempRepository.findDataInCurrentWeek(device.getMacAddress());
                    return executeData(reports1, reportTemp1);
                case 2:
                    // by last week
                    List<Report> reports2 = reportRepository.findDataInLastWeek(device.getMacAddress());
                    ReportTemp reportTemp2 = reportTempRepository.findDataInLastWeek(device.getMacAddress());
                    return executeData(reports2, reportTemp2);
                case 3:
                    // by this month
                    List<Report> reports3 = reportRepository.findDataInThisMonth(device.getMacAddress());
                    ReportTemp reportTemp3 = reportTempRepository.findDataInThisMonth(device.getMacAddress());
                    List<ReportData> reportDataList3 = executeData(reports3, reportTemp3);
                    return parseDataByWeek(reportDataList3);
                case 4:
                    // by last month
                    List<Report> reports4 = reportRepository.findDataInLastMonth(device.getMacAddress());
                    ReportTemp reportTemp4 = reportTempRepository.findDataInLastMonth(device.getMacAddress());
                    List<ReportData> reportDataList4 = executeData(reports4, reportTemp4);
                    return parseDataByWeek(reportDataList4);
                case 5:
                    // by last 3 month, last 6 month, this year, last year
                    List<ReportData> reportDataList5 = new ArrayList<>();
                    // last 3 month
                    List<Report> reports5 = reportRepository.findDataInLastThreeMonth(device.getMacAddress());
                    ReportTemp reportTemp5 = reportTempRepository.findDataInLastThreeMonth(device.getMacAddress());
                    Report report3Month = new Report();
                    report3Month.setTimeRetirement(reportTemp5.getTimeRetirement());
                    report3Month.setTimeWork(reportTemp5.getTimeWork());
                    reports5.add(report3Month);

                    String timeWork3Month = convertTimeToString(reports5, "timeWork");
                    String timeRetirement3Month = convertTimeToString(reports5, "timeRetirement");
                    ReportData data3Month = new ReportData(timeWork3Month, timeRetirement3Month, "Last 3 months");

                    reportDataList5.add(data3Month);

                    // last 6 month
                    List<Report> reports6 = reportRepository.findDataInLastSixMonth(device.getMacAddress());
                    ReportTemp reportTemp6 = reportTempRepository.findDataInLastSixMonth(device.getMacAddress());
                    Report report6Month = new Report();
                    report6Month.setTimeRetirement(reportTemp6.getTimeRetirement());
                    report6Month.setTimeWork(reportTemp6.getTimeWork());
                    reports6.add(report6Month);

                    String timeWork = convertTimeToString(reports6, "timeWork");
                    String timeRetirement = convertTimeToString(reports6, "timeRetirement");
                    ReportData data6Month = new ReportData(timeWork, timeRetirement, "Last 6 months");
                    reportDataList5.add(data6Month);

                    return reportDataList5;


            }
        }

    return null;

    }

    public List<ReportData> parseDataByWeek(List<ReportData> reportDataList) {
        List<ReportData> dataByWeek = new ArrayList<>();

        int i = 0;
        int j = 1;
        while (i < reportDataList.size()) {
            List<LocalDate> StartAndEndWeekData = countStartAndEndWeek(reportDataList.get(i).getDateWork());
            int timeWorkInSecond = 0;
            int timeRetirementInSecond = 0;
            String label = "Week " + j + " (" + StartAndEndWeekData.get(0).toString() + " - " + StartAndEndWeekData.get(1).toString() + ")";
            String username = reportDataList.get(i).getUsername();
            String macAddress = reportDataList.get(i).getMacAddress();
            while (i < reportDataList.size() && reportDataList.get(i).getDateWork().compareTo(StartAndEndWeekData.get(0)) >= 0 && reportDataList.get(i).getDateWork().compareTo(StartAndEndWeekData.get(1)) <= 0) {
                if (i < reportDataList.size()) {
                    timeWorkInSecond += LocalTime.parse(reportDataList.get(i).getTimeWork()).toSecondOfDay();
                    timeRetirementInSecond += LocalTime.parse(reportDataList.get(i).getTimeRetirement()).toSecondOfDay();
                    i++;
                }
            }
            // parse to ReportData

            String timeWork = convertSecondToTimeString(timeWorkInSecond);
            String timeRetirement = convertSecondToTimeString(timeRetirementInSecond);
            ReportData data = new ReportData(username, timeWork, timeRetirement, macAddress, label);
            dataByWeek.add(data);
            j++;
        }
        return dataByWeek;
    }

    public String convertSecondToTimeString(int second) {
        int hrs = (int) TimeUnit.SECONDS.toHours(second);
        int min = (int) TimeUnit.SECONDS.toMinutes(second) % 60;
        int sec = (int) TimeUnit.SECONDS.toSeconds(second) % 60;

        return String.format("%02d:%02d:%02d", hrs, min, sec);
    }

    public List<LocalDate> countStartAndEndWeek(LocalDate date) {
        LocalDate monday = date.with(DayOfWeek.MONDAY);
        while (monday.getMonth().compareTo(date.getMonth()) != 0) {
            monday = monday.plusDays(1);
        }

        LocalDate sunday = date.with(DayOfWeek.SUNDAY);
        while (sunday.getMonth().compareTo(date.getMonth()) != 0) {
            sunday = sunday.minusDays(1);
        }
        List<LocalDate> data = new ArrayList<>();
        data.add(monday);
        data.add(sunday);
        return data;
    }

    public String convertTimeToString(List<Report> reports, String time) {
        long total = reports.stream().mapToLong(report -> {
            long timeInMilliseconds = 0;
            if(time == "timeWork") {
                timeInMilliseconds = report.getTimeWork();
            } else if (time == "timeRetirement") {
                timeInMilliseconds = report.getTimeRetirement();
            }
            return timeInMilliseconds;
        }).sum();

        int hrs = (int) TimeUnit.MILLISECONDS.toHours(total);
        int min = (int) TimeUnit.MILLISECONDS.toMinutes(total) % 60;
        int sec = (int) TimeUnit.MILLISECONDS.toSeconds(total) % 60;

        return String.format("%02d:%02d:%02d", hrs, min, sec);
    }

    public String getAllTimeRetirement() {
        List<Report> reports = (reportRepository.findAll());
        String timeRetirement = "timeRetirement";

        return convertTimeToString(reports, timeRetirement);
    }

    public List<ReportData> getReportWorkBetween(String startDate, String endDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, dtf);
        LocalDate end = LocalDate.parse(endDate, dtf);
        List<ReportData> reportDataList = new ArrayList<>();


        List<ReportTemp> reportTempList = reportTempRepository.findAllByDateWorkBetween(start, end);
        for (int i = 0; i < reportTempList.size(); i++) {
            LocalTime timeWork = LocalTime.ofSecondOfDay(Math.round(reportTempList.get(i).getTimeWork() / 1000));
            LocalTime startTimeWork = LocalTime.ofSecondOfDay(Math.round(reportTempList.get(i).getStartTimeWork() / 1000));
            LocalTime timeRetirement = LocalTime.ofSecondOfDay(Math.round(reportTempList.get(i).getTimeRetirement() / 1000));
            ReportData reportData = new ReportData(reportRepository.findByMacAddress(reportTempList.get(i).getMacAddress()).get(0).getUsername(), reportTempList.get(i).getDateWork(), timeWork.toString(), startTimeWork.toString(), timeRetirement.toString(), reportTempList.get(i).getMacAddress());
            reportDataList.add(reportData);
        }

        List<Report> reports = reportRepository.findAllByDateWorkBetween(start, end);
        for (int i = 0; i < reports.size(); i++) {

            LocalTime timeWork = LocalTime.ofSecondOfDay(Math.round(reports.get(i).getTimeWork() / 1000));
            LocalTime startTimeWork = LocalTime.ofSecondOfDay(Math.round(reports.get(i).getStartTimeWork() / 1000));
            LocalTime timeRetirement = LocalTime.ofSecondOfDay(Math.round(reports.get(i).getTimeRetirement() / 1000));
            ReportData reportData = new ReportData(reports.get(i).getUsername(), reports.get(i).getDateWork(), timeWork.toString(), startTimeWork.toString(), timeRetirement.toString(), reports.get(i).getMacAddress());
            reportDataList.add(reportData);


        }
        return reportDataList;
    }

    public String getAllTimeWorkByUsername(String username) {
        List<Report> reports = reportRepository.findAllByUsername(username);
        String timeWork = "timeWork";

        return convertTimeToString(reports, timeWork);
    }

    public String getALlTimeRetirementByUsername(String username) {
        List<Report> reports = reportRepository.findAllByUsername(username);
        String timeRetirement = "timeRetirement";

        return convertTimeToString(reports, timeRetirement);
    }

    public String getTimeWorkBetweenDate(String startDate, String endDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, dtf);
        LocalDate end = LocalDate.parse(endDate, dtf);

        List<ReportTemp> reportTempList = reportTempRepository.findAllByDateWorkBetween(start, end);

        List<Report> reports = reportRepository.findAllByDateWorkBetween(start, end);

        for (int i = 0; i < reportTempList.size(); i++) {
            Report report = new Report();
            report.setTimeWork(reportTempList.get(i).getTimeWork());
            report.setTimeRetirement(reportTempList.get(i).getTimeRetirement());
            reports.add(report);
        }

        String timeWork = "timeWork";

        return convertTimeToString(reports, timeWork);
    }

    public String getTimeRetirementBetweenDate(String startDate, String endDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, dtf);
        LocalDate end = LocalDate.parse(endDate, dtf);

        List<ReportTemp> reportTempList = reportTempRepository.findAllByDateWorkBetween(start, end);
        List<Report> reports = reportRepository.findAllByDateWorkBetween(start, end);

        for (int i = 0; i < reportTempList.size(); i++) {
            Report report = new Report();
            report.setTimeWork(reportTempList.get(i).getTimeWork());
            report.setTimeRetirement(reportTempList.get(i).getTimeRetirement());
            reports.add(report);
        }

        String timeRetirement = "timeRetirement";

        return convertTimeToString(reports, timeRetirement);
    }
}
