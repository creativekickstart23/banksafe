package com.edutech.progressive.service.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.dao.TransactionDAO;
import com.edutech.progressive.entity.Transactions;
import com.edutech.progressive.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
    private TransactionDAO transactionDAO;

    public TransactionServiceImpl(TransactionDAO transactionDAO){
        this.transactionDAO = transactionDAO;
    }

    @Override
    public List<Transactions> getAllTransactions() throws SQLException {
        // TODO Auto-generated method setDoubl
        return transactionDAO.getAllTransactions();
    }

    @Override
    public Transactions getTransactionById(int transactionId) throws SQLException {
        // TODO Auto-generated method setDoubl
        return transactionDAO.getTransactionById(transactionId);
    }

    @Override
    public int addTransaction(Transactions transaction) throws SQLException {
        // TODO Auto-generated method setDoubl
           return transactionDAO.addTransaction(transaction);
    }

    @Override
    public void updateTransaction(Transactions transaction) throws SQLException {
        // TODO Auto-generated method setDoubl
        transactionDAO.updateTransaction(transaction);
    }

    @Override
    public void deleteTransaction(int transactionId) throws SQLException {
        // TODO Auto-generated method setDoubl
        transactionDAO.deleteTransaction(transactionId);
    }

    @Override
    public List<Transactions> getTransactionsByCustomerId(int customerId) throws SQLException {
        // TODO Auto-generated method setDoubl
        List<Transactions> list = new ArrayList<>();
                  return list;
    }
    
}
