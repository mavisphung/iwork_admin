package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.AccountInfo;
import com.dfksoft.hrm_manage.repository.AccountInfoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountInfoService {
    private final AccountInfoRepository accountInfoRepository;

    public AccountInfoService(AccountInfoRepository accountInfoRepository) {
        this.accountInfoRepository = accountInfoRepository;
    }

    public List<AccountInfo> getAllAccountInfo() {
        return (List<AccountInfo>) accountInfoRepository.findAll();
    }

    //add new account info
    public AccountInfo addNewAccountInfo(String firstName, String lastName, String sex, String address, String phone, int accountId) {
        AccountInfo accountInfo = new AccountInfo();

        accountInfo.setFirstName(firstName);
        accountInfo.setLastName(lastName);
        accountInfo.setSex(sex);
        accountInfo.setAddress(address);
        accountInfo.setPhone(phone);
        accountInfo.setAccountID(accountId);

        return accountInfoRepository.saveAndFlush(accountInfo);
    }

    @Transactional
    public void deleteAccountInfo(int id) {
        accountInfoRepository.deleteAccountInfoByAccountID(id);
    }

    @Transactional
    public AccountInfo getAccountInfoByAccountId(int accountId) {
        return (AccountInfo) accountInfoRepository.findAccountInfoByAccountID(accountId);
    }

    // update account info
    public  AccountInfo updateAccountInfoByFindByAccountId(int accountId, String firstName, String lastName, String sex, String address, String phone) {
        AccountInfo accountInfo = accountInfoRepository.findAccountInfoByAccountID(accountId);

        accountInfo.setFirstName(firstName);
        accountInfo.setLastName(lastName);
        accountInfo.setSex(sex);
        accountInfo.setAddress(address);
        accountInfo.setPhone(phone);

        return accountInfoRepository.saveAndFlush(accountInfo);
    }
}
