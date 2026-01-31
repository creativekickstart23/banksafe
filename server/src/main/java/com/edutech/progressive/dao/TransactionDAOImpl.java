package com.edutech.progressive.dao;

import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.entity.Transactions;

public class TransactionDAOImpl implements TransactionDAO{

    List<Transactions> transactionsList=new ArrayList<>();
    @Override
    public int addTransaction(Transactions transaction) {
        return -1;
    }

    @Override
    public Transactions getTransactionById(int transactionId) {
        return null;
    }

    @Override
    public void updateTransaction(Transactions transaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTransaction'");
    }

    @Override
    public void deleteTransaction(int transactionId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTransaction'");
    }

    @Override
    public List<Transactions> getAllTransactions() {
        return transactionsList;
    }

}
