package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.service.AccountService;

public class AccountDAOImpl implements AccountDAO{
   
    private Connection connection;
    public AccountDAOImpl(){
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }


    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        String sql="INSERT INTO accounts(customer_id,balance) VALUES(?,?)";
        PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, accounts.getCustomerId());
            ps.setDouble(2, accounts.getBalance());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
            return -1;

    }

    @Override
    public Accounts getAccountById(int accountId) throws SQLException {
        String sql="SELECT * FROM accounts WHERE account_id=?";
       PreparedStatement ps = connection.prepareStatement(sql);
     
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Accounts(rs.getInt("account_id"), rs.getInt("customer_id"), rs.getDouble("balance"));
            }
            return null;

            
       
    }

    @Override
    public void updateAccount(Accounts accounts) throws SQLException {
        String sql="UPDATE accounts SET  balance=? WHERE account_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, accounts.getBalance());
            ps.setInt(2, accounts.getAccountId());
            ps.executeUpdate();
            
      
    }

    @Override
    public void deleteAccount(int accountId) throws SQLException {
        String sql="DELETE FROM accounts WHERE account_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
             ps.setInt(1, accountId);
              ps.executeUpdate();
             

      
    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        List<Accounts> list = new ArrayList<>();
        String sql="SELECT * FROM accounts";
        PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              Accounts acc =   new Accounts(rs.getInt("account_id"), rs.getInt("customer_id"), rs.getDouble("balance"));
              list.add(acc);
            }
            return list;
        
    }
    
}


// ___________________________________________ NEW CHANGES DONE__________________________________________
