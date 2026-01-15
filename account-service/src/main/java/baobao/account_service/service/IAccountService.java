package baobao.account_service.service;

import baobao.account_service.entity.Account;

import java.util.List;

public interface IAccountService {
    Account findAccountById(int id);

    List<Account> getListAccounts();
}
