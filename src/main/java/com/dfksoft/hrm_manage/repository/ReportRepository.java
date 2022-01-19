package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    List<Report> findAllByDateWorkBetween(LocalDate startDate, LocalDate endDate);

    List<Report> findAllByUsername(String username);

    List<Report> findAllByUsernameOrderByDateWorkAsc(String username);

    List<Report> findByMacAddress(String macaddress);

    String currentWeekQuery = "SELECT * FROM report rp where YEARWEEK(rp.date_work, 1) = YEARWEEK(CURDATE(), 1) and rp.date_work <= curdate() and rp.mac_address = :mac order by rp.date_work";
    @Query(value = currentWeekQuery, nativeQuery = true)
    List<Report> findDataInCurrentWeek(@Param("mac") String mac);

    String lastWeekQuery = "SELECT * FROM report rp\n" +
            "WHERE YEARWEEK(rp.date_work) = YEARWEEK(NOW() - INTERVAL 2 WEEK) AND rp.mac_address = :mac\n" +
            "order by rp.date_work";
    @Query(value = lastWeekQuery, nativeQuery = true)
    List<Report> findDataInLastWeek(@Param("mac") String mac);

    String thisMonthQuery = "SELECT * FROM report rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work between DATE_FORMAT(CURRENT_DATE, '%Y-%m-01') and CURRENT_DATE\n" +
            "order by rp.date_work";
    @Query(value = thisMonthQuery, nativeQuery = true)
    List<Report> findDataInThisMonth(@Param("mac") String mac);

    String lastMonthQuery = "SELECT * FROM report rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work between DATE_FORMAT(CURRENT_DATE - INTERVAL 1 MONTH, '%Y-%m-01') and LAST_DAY(CURRENT_DATE - INTERVAL 1 MONTH)\n" +
            "order by rp.date_work";
    @Query(value = lastMonthQuery, nativeQuery = true)
    List<Report> findDataInLastMonth(@Param("mac") String mac);

    String lastThreeMonthQuery = "SELECT * FROM report rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work >= DATE_FORMAT(CURRENT_DATE - INTERVAL 3 MONTH, '%Y-%m-01')\n" +
            "order by rp.date_work";
    @Query(value = lastThreeMonthQuery, nativeQuery = true)
    List<Report> findDataInLastThreeMonth(@Param("mac") String mac);

    String lastSixMonthQuery = "SELECT * FROM report rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work >= DATE_FORMAT(CURRENT_DATE - INTERVAL 6 MONTH, '%Y-%m-01')\n" +
            "order by rp.date_work";
    @Query(value = lastSixMonthQuery, nativeQuery = true)
    List<Report> findDataInLastSixMonth(@Param("mac") String mac);

    String thisYearQuery = "SELECT * FROM report rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work between DATE_FORMAT(CURRENT_DATE, '%Y-01-01') and CURRENT_DATE\n" +
            "order by rp.date_work";
    @Query(value = thisYearQuery, nativeQuery = true)
    List<Report> findDataInThisYear(@Param("mac") String mac);

    String lastYearQuery = "SELECT * FROM report rp\n" +
            "WHERE rp.mac_address = :mac AND rp.date_work between DATE_FORMAT(CURRENT_DATE - INTERVAL 1 YEAR, '%Y-01-01') and DATE_FORMAT(CURRENT_DATE - INTERVAL 1 YEAR, '%Y-12-31')\n" +
            "order by rp.date_work";
    @Query(value = lastYearQuery, nativeQuery = true)
    List<Report> findDataInLastYear(@Param("mac") String mac);
}
