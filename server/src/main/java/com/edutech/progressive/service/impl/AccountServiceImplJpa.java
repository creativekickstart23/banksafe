package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.edutech.progressive.dao.AccountDAO;
import com.edutech.progressive.entity.Accounts;

public class AccountServiceImplJpa {

    private AccountDAO accountDAO;

    public AccountServiceImplJpa(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public List<Accounts> getAllAccounts() throws SQLException {
        return accountDAO.getAllAccounts();
    }

    public Accounts getAccountById(int accountId) throws SQLException {
        return accountDAO.getAccountById(accountId);
    }

    public int addAccount(Accounts accounts) throws SQLException {
        return accountDAO.addAccount(accounts);
    }

    public void updateAccount(Accounts accounts) throws SQLException {
        accountDAO.updateAccount(accounts);
    }

    public void deleteAccount(int accountId) throws SQLException {
        accountDAO.deleteAccount(accountId);
    }

    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
        List<Accounts> list = accountDAO.getAllAccounts();
        list.sort((a, b) ->
                Double.compare(a.getBalance(), b.getBalance()));
        return list;
    }
}
