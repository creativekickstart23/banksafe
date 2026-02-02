package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.entity.Accounts;

public class AccountServiceImplArraylist {

    private static List<Accounts> accountsList;

    public AccountServiceImplArraylist() {
        accountsList = new ArrayList<>();
    }

    public List<Accounts> getAllAccounts() {
        return accountsList;
    }

    public List<Accounts> getAllAccountsSortedByBalance() {
        accountsList.sort((a, b) ->
                Double.compare(a.getBalance(), b.getBalance()));
        return accountsList;
    }

    public int addAccount(Accounts accounts) {
        accountsList.add(accounts);
        return accountsList.size();
    }

    public void emptyArrayList() {
        accountsList.clear();
    }
}
