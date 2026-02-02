package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Transactions;

public class TransactionDAOImpl implements TransactionDAO {

    private Connection connection;

    public TransactionDAOImpl(){
         try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    

    @Override
    public int addTransaction(Transactions transaction) throws SQLException {
        // TODO Auto-generated method setDouble
        String sql="INSERT INTO transactions(account_id, amount,transaction_date, transaction_type) VALUES(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, transaction.getAccountId());
        ps.setDouble(2,transaction.getAmount());
        ps.setTimestamp(3, new Timestamp(transaction.getTransactionDate().getTime()));
        ps.setString(4, transaction.getTransactionType());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            int id= rs.getInt(1);
            transaction.setTransactionId(id);
            return id;
        }
        return -1;
           
    }

    @Override
    public Transactions getTransactionById(int transactionId) throws SQLException {
        
        String sql="SELECT * FROM transactions WHERE transaction_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, transactionId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return new Transactions(rs.getInt("transaction_id"), rs.getInt("account_id"), rs.getDouble("amount"), rs.getTimestamp("transaction_date"), rs.getString("transaction_type"));
        }
        return null;
    }

    @Override
    public void updateTransaction(Transactions transaction) throws SQLException {
        
        String sql="UPDATE transactions SET amount=? , transaction_type=? WHERE transaction_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setDouble(1, transaction.getAmount());
        ps.setString(2,transaction.getTransactionType());
        // ps.setTimestamp(3, new Timestamp(transaction.getTransactionDate().getTime()));
        ps.setInt(3, transaction.getTransactionId());
        ps.executeUpdate();

    }

    @Override
    public void deleteTransaction(int transactionId) throws SQLException {
        String sql="DELETE FROM transactions WHERE transaction_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, transactionId);
        ps.executeUpdate();
    }

    @Override
    public List<Transactions> getAllTransactions() throws SQLException {
        List<Transactions> list = new ArrayList<>();
        String sql="SELECT * FROM transactions";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Transactions t = new  Transactions(rs.getInt("transaction_id"), rs.getInt("account_id"), rs.getDouble("amount"), rs.getTimestamp("transaction_date"), rs.getString("transaction_type"));
          list.add(t);
        }
        return list;
    }




}
