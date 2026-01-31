package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.entity.Transactions;
import com.edutech.progressive.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
    List<Transactions> transactionsList;
    @Override
    public List<Transactions> getAllTransactions() throws SQLException {
        return new ArrayList<>();
    }

    @Override
    public Transactions getTransactionById(int transactionId) throws SQLException {
        return null;
    }

    @Override
    public int addTransaction(Transactions transaction) throws SQLException {
        return -1;
    }

    @Override
    public void updateTransaction(Transactions transaction) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTransaction'");
    }

    @Override
    public void deleteTransaction(int transactionId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTransaction'");
    }

    @Override
    public List<Transactions> getTransactionsByCustomerId(int customerId) throws SQLException {
        return null;
    }
    
}