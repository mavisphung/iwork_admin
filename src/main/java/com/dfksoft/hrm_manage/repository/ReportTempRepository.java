package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.Report;
import com.dfksoft.hrm_manage.entity.ReportTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReportTempRepository extends JpaRepository<ReportTemp, String> {
    List<ReportTemp> findAllByDateWorkBetween(LocalDate startDate, LocalDate endDate);

    List<ReportTemp> findAllByMacAddressAndDateWorkBetween(String macAddress, LocalTime startDate, LocalTime endDate);

    List<ReportTemp> findAllByMacAddress(String macAddress);

    ReportTemp findByMacAddress(String macAddress);


    String currentWeekQuery = "SELECT * FROM report_temp rp where YEARWEEK(rp.date_work, 1) = YEARWEEK(CURDATE(), 1) and rp.date_work <= curdate() and rp.mac_address = :mac";
    @Query(value = currentWeekQuery, nativeQuery = true)
    ReportTemp findDataInCurrentWeek(@Param("mac") String mac);

    String lastWeekQuery = "SELECT * FROM report_temp rp\n" +
            "WHERE YEARWEEK(rp.date_work) = YEARWEEK(NOW() - INTERVAL 2 WEEK) AND rp.mac_address = :mac";
    @Query(value = lastWeekQuery, nativeQuery = true)
    ReportTemp findDataInLastWeek(@Param("mac") String mac);

    String thisMonthQuery = "SELECT * FROM report_temp rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work between DATE_FORMAT(CURRENT_DATE, '%Y-%m-01') and CURRENT_DATE";
    @Query(value = thisMonthQuery, nativeQuery = true)
    ReportTemp findDataInThisMonth(@Param("mac") String mac);

    String lastMonthQuery = "SELECT * FROM report_temp rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work between DATE_FORMAT(CURRENT_DATE - INTERVAL 1 MONTH, '%Y-%m-01') and LAST_DAY(CURRENT_DATE - INTERVAL 1 MONTH)";
    @Query(value = lastMonthQuery, nativeQuery = true)
    ReportTemp findDataInLastMonth(@Param("mac") String mac);

    String lastThreeMonthQuery = "SELECT * FROM report_temp rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work >= DATE_FORMAT(CURRENT_DATE - INTERVAL 3 MONTH, '%Y-%m-01')\n";
    @Query(value = lastThreeMonthQuery, nativeQuery = true)
    ReportTemp findDataInLastThreeMonth(@Param("mac") String mac);

    String lastSixMonthQuery = "SELECT * FROM report_temp rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work >= DATE_FORMAT(CURRENT_DATE - INTERVAL 6 MONTH, '%Y-%m-01')\n";
    @Query(value = lastSixMonthQuery, nativeQuery = true)
    ReportTemp findDataInLastSixMonth(@Param("mac") String mac);

    String thisYearQuery = "SELECT * FROM report_temp rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work between DATE_FORMAT(CURRENT_DATE, '%Y-01-01') and CURRENT_DATE\n";
    @Query(value = thisYearQuery, nativeQuery = true)
    ReportTemp findDataInThisYear(@Param("mac") String mac);

    String lastYearQuery = "SELECT * FROM report_temp rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work between DATE_FORMAT(CURRENT_DATE - INTERVAL 1 YEAR, '%Y-01-01') and DATE_FORMAT(CURRENT_DATE - INTERVAL 1 YEAR, '%Y-12-31')\n";
    @Query(value = lastYearQuery, nativeQuery = true)
    ReportTemp findDataInLastYear(@Param("mac") String mac);

}
