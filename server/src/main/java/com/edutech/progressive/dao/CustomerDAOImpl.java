package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.dto.CustomerAccountInfo;
import com.edutech.progressive.entity.Customers;

public class CustomerDAOImpl implements CustomerDAO{

    private Connection connection;
    public CustomerDAOImpl(){
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }

    @Override
    public int addCustomer(Customers customer) throws SQLException {
           String sql="INSERT INTO customers (name,email,username,password) VALUES(?,?,?,?) ";
        PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, customer.getName());
        ps.setString(2,customer.getEmail());
        ps.setString(3,customer.getUsername());
        ps.setString(4,customer.getPassword());
        // ps.setString(5, customer.getRole());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        
        return -1;   
        
    }

    @Override
    public Customers getCustomerById(int customerId) throws SQLException {
        // TODO Auto-generated method setDouble
        String sql="SELECT * FROM customers WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return new Customers(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
        }
        return null;



    }

    @Override
    public void updateCustomer(Customers customers) throws SQLException {
        String sql="UPDATE customers SET name=?,email=?,username=?,password=? WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customers.getName());
        ps.setString(2,customers.getEmail());
        ps.setString(3,customers.getUsername());
        ps.setString(4,customers.getPassword());
        ps.setInt(5, customers.getCustomerId());
        // ps.setString(5, customer.getRole());
        ps.executeUpdate();
        
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        // TODO Auto-generated method setDouble
        String sql="DELETE FROM customers WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,customerId);
        ps.executeUpdate();

    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        // TODO Auto-generated method setDouble
        List<Customers> list = new ArrayList<>();
        String sql="SELECT * FROM customers";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Customers cust = new  Customers(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
            list.add(cust);
        }
        return list;
        

    }

    @Override
    public CustomerAccountInfo getCustomerAccountInfo(int customerId) throws SQLException {
        // TODO Auto-generated method setDouble
        String sql="SELECT c.customer_id , c.name,c.email,a.account_id,a.balance " + 
        "FROM customers c JOIN  accounts a ON c.customer_id = a.customer_id " +
        "WHERE c.customer_id=?"
        ;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return new  CustomerAccountInfo(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"), rs.getInt("account_id"),rs.getDouble("balance"));
            // list.add(cust);
        }
        return null;

        
    }



}
