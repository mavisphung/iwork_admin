package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, Integer> {

    Integer deleteAccountInfoByAccountID(Integer accountId);

    AccountInfo findAccountInfoByAccountID(Integer accountId);
}
