package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.service.AccountService;

public class AccountServiceImplArraylist implements AccountService {
    public static List<Accounts> accountsList = new ArrayList<>();

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountsList;
    }

    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        accountsList.add(accounts);
        return accountsList.size();
    }

    @Override
    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
        Collections.sort(accountsList);
        return accountsList;
    }

    @Override
    public void emptyArrayList() {
        accountsList.clear();
    }
}
