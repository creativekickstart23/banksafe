package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Accounts;

public class AccountDAOImpl implements AccountDAO{
    List<Accounts>accountsList=new ArrayList<Accounts>();

    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        return -1;
    }

    @Override
    public Accounts getAccountById(int accountId) throws SQLException {

        return null;
    }

    @Override
    public void updateAccount(Accounts accounts) throws SQLException {

    }

    @Override
    public void deleteAccount(int accountId) {

    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountsList;
    }

    public List<Accounts> getAllAccountsByCustomer(int customer_id) throws SQLException {
        List<Accounts> act=new ArrayList<>();
        String str="SELECT * FROM accounts WHERE customer_id=?";
        Connection connection=DatabaseConnectionManager.getConnection();

        PreparedStatement ps=connection.prepareStatement(str);
        ps.setInt(1, customer_id);
        ResultSet rs=ps.executeQuery();

        while(rs.next()){
            int aid=rs.getInt("account_id");
            int cid=rs.getInt("customer_id");
            Double bal=rs.getDouble("balance");
            Accounts a=new Accounts(aid,cid,bal);
            act.add(a);
        }
        return act;
    }
}
