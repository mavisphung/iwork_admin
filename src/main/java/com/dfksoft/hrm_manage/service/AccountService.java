package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.Account;
import com.dfksoft.hrm_manage.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccount() {
        return (List<Account>) accountRepository.findAll();
    }

    public Account findAccountById(int id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        return optionalAccount.orElseGet(Account::new);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }


    //create account
    public Account initAccount(String username, String password, int roleId, int companyId, int status, int titleId) {
        Account account = new Account();

        account.setUsername(username);
        account.setPassword(password);
        account.setRoleId(roleId);
        account.setTitleId(titleId);
        account.setCompanyId(companyId);
        account.setStatus(status);

        return accountRepository.saveAndFlush(account);
    }

    // update account
    public  Account updateAccount(int id, int roleId, int titleId, int status, int companyId) {
        Account account = accountRepository.findAccountById(id);

        account.setRoleId(roleId);
        account.setTitleId(titleId);
        account.setStatus(status);
        account.setCompanyId(companyId);

        return accountRepository.saveAndFlush(account);
    }
}
