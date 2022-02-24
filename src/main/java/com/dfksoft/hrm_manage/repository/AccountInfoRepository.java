package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, Integer> {

    Integer deleteAccountInfoByAccountID(Integer accountId);

    AccountInfo findAccountInfoByAccountID(Integer accountId);

    // @Query(
    //     value = "SELECT ai FROM AccountInfo ai, Device d WHERE ai.accountId = d.accountId AND d.macAddress = :macAddress"
    // )
    // @Query("SELECT ai FROM AccountInfo ai join Device d on ai.accountId = d.accountId where d.macAddress = :macAddress")
    @Query(value = "select ai from AccountInfo ai join Device d on d.accountId = ai.accountID where d.macAddress = ?1")
    List<AccountInfo> findAccountInfoByMacAddress(@Param("macAddress") String macAddress);
}
