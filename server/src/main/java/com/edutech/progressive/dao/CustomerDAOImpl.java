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
 
    List<Customers> customerList = new ArrayList<>();
 
    private Connection connection;
 
    public CustomerDAOImpl(){
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /////////////////////done---1
    @Override
    public int addCustomer(Customers customers) throws SQLException{
        String sql = "INSERT INTO customers(name,email,username,password,role) VALUES(?,?,?,?,?)";
 
        try(PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
 
            ps.setString(1,customers.getName());
            ps.setString(2,customers.getEmail());
            ps.setString(3,customers.getUsername());
            ps.setString(4,customers.getPassword());
            ps.setString(5,customers.getRole());
            ps.executeUpdate();
 
            ResultSet rs = ps.getGeneratedKeys();
 
            if(rs.next()){
                customers.setCustomerId(rs.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return customers.getCustomerId();
    }
 
    //////////////////done
    @Override
    public Customers getCustomerById(int customerId)throws SQLException {
        Customers customer = null;
        String sql = "Select * from customers where customer_id=?";
 
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,customerId);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                customer = new Customers(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
            }
        }catch(SQLException e){
                e.printStackTrace();
        }
        return customer;
    }
 
    /////////////////////done
    @Override
    public void updateCustomer(Customers customers) throws SQLException{
        String sql = "UPDATE customers set name=?,email=?,username=?,password=?,role=? where customer_id=?";
 
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,customers.getName());
            ps.setString(2,customers.getEmail());
            ps.setString(3,customers.getUsername());
            ps.setString(4,customers.getPassword());
            ps.setString(5,customers.getRole());
            ps.setInt(6,customers.getCustomerId());
            ps.executeUpdate();
        }catch(SQLException e){
                e.printStackTrace();
        }
    }
 
 
    /////////////////done
    @Override
    public void deleteCustomer(int customerId) throws SQLException{
        String sql = "DELETE from customers where customer_id=?";
 
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,customerId);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
 
 
    /////////////////done
    @Override
    public List<Customers> getAllCustomers()throws SQLException {
        List<Customers> customerList = new ArrayList<>();
        String sql = "Select * from customers";
 
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Customers customer = new Customers(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
                customerList.add(customer);
            }
 
        }catch(SQLException e){
                e.printStackTrace();
        }
 
        return customerList;
    }
 
 
    ///////////////////////will do Later after CustomerAccountInfo class completion ////done
    @Override
    public CustomerAccountInfo getCustomerAccountInfo(int customerId) throws SQLException{
        String sql = "Select c.customer_id,c.name,c.email,a.account_id,a.balance " + "FROM customers c JOIN accounts a ON c.customer_id=a.customer_id " + "Where c.customer_id=?";
 
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,customerId);
            ResultSet rs = ps.executeQuery();
 
            if(rs.next()){
                return new CustomerAccountInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5));
            }
        }
 
        return null;
    }
 
}
