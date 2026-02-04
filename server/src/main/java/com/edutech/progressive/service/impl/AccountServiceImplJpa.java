// package com.edutech.progressive.service.impl;

// import java.sql.SQLException;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.edutech.progressive.dao.AccountDAO;
// import com.edutech.progressive.entity.Accounts;
// import com.edutech.progressive.repository.AccountRepository;
// import com.edutech.progressive.service.AccountService;
// @Service
// public class AccountServiceImplJpa implements AccountService {

//     private final AccountRepository accRepo;
//     public AccountServiceImplJpa(AccountRepository accRepo) {
//         this.accRepo = accRepo;
//     }

//     @Override
//     public List<Accounts> getAllAccounts() throws SQLException {
//         return accRepo.findAll();
//     }

//     @Override
//     public Accounts getAccountById(int accountId) throws SQLException {
//         return accRepo.findById(accountId).orElse(null);
//     }

//     @Override
//     public int addAccount(Accounts accounts) throws SQLException {
//         return accRepo.save(accounts).getAccountId();
//     }

//     @Override
//     public void updateAccount(Accounts accounts) throws SQLException {
//         accRepo.save(accounts);
//     }

//     @Override
//     public void deleteAccount(int accountId) throws SQLException {
//         accRepo.deleteById(accountId);
//     }

//     @Override
//     public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
//         List<Accounts> list = accRepo.findAll();
//         list.sort(Accounts::compareTo);
//         return list;
//     }

//     @Override
//     public List<Accounts> getAccountsByUser(int userId) throws SQLException{
//         return accRepo.findAll();
//     }
// }



package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.dao.AccountDAO;
import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.repository.AccountRepository;
import com.edutech.progressive.service.AccountService;
@Service
public class AccountServiceImplJpa implements AccountService {

    private final AccountRepository accRepo;
    public AccountServiceImplJpa(AccountRepository accRepo) {
        this.accRepo = accRepo;
    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accRepo.findAll();
    }

    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        return accRepo.save(accounts).getAccountId();
    }

    @Override
    public void updateAccount(Accounts accounts) throws SQLException {
        accRepo.save(accounts);
    }

    @Override
    public void deleteAccount(int accountId) throws SQLException {
        accRepo.deleteById(accountId);
    }

    @Override
    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
        List<Accounts> list = accRepo.findAll();
        list.sort(Accounts::compareTo);
        return list;
    }
}