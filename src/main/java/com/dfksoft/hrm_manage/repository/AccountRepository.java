package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(int id);

    Account findByUsername(String username);

}
