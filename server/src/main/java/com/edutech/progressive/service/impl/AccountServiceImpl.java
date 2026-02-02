package com.edutech.progressive.service.impl;
 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
import com.edutech.progressive.dao.AccountDAO;
import com.edutech.progressive.dao.AccountDAOImpl;
import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.service.AccountService;
 
public class AccountServiceImpl implements AccountService{
 
    private AccountDAO accountDAO;
    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
 
    private static List<Accounts> accountsList = new ArrayList<>();
 
    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountDAO.getAllAccounts();
    }
 
    @Override
    public int addAccount(Accounts accounts) throws SQLException {
 
        return accountDAO.addAccount(accounts);
    }
 
    @Override
    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
            accountsList = accountDAO.getAllAccounts();
            Collections.sort(accountsList);
            return accountsList;
    }
 
    public List<Accounts> getAccountsByUser(int userId) throws SQLException{
            return accountDAO.getAllAccountsByCustomers(userId);
    }
 
    public Accounts getAccountById(int accountId) throws SQLException{
        Accounts account = accountDAO.getAccountById(accountId);
 
        return account;
    }
 
    public void updateAccount(Accounts accounts)throws SQLException{
        accountDAO.updateAccount(accounts);
    }
 
    public void deleteAccount(int accountId) throws SQLException{
        accountDAO.deleteAccount(accountId);
    }
}
