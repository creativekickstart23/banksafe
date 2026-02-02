package com.edutech.progressive.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
import org.springframework.data.jpa.repository.Modifying;
 
import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Accounts;
 
public class AccountDAOImpl implements AccountDAO {
 
    private List<Accounts> accountsList = new ArrayList<>();
 
    // private Connection connection;
 
    // public AccountDAOImpl() {
    //     try {
    //         this.connection = DatabaseConnectionManager.getConnection();
    //     } catch (SQLException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    // }
 
    ////////////////////done
    @Override
    public int addAccount(Accounts accounts)throws SQLException{
        String sql = "INSERT INTO accounts(customer_id,balance) VALUES(?,?)";
 
        try(Connection connection1 = DatabaseConnectionManager.getConnection();PreparedStatement ps = connection1.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,accounts.getCustomerId());
            ps.setDouble(2,accounts.getBalance());
            ps.executeUpdate();
 
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                accounts.setAccountId(rs.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return accounts.getAccountId();
    }
 
    ///////////////////////done
    @Override
    public Accounts getAccountById(int accountId)throws SQLException{
        //Accounts account = null;
        String sql = "SELECT * FROM accounts where account_id=?";
 
        

 
        try(Connection connection1 = DatabaseConnectionManager.getConnection();PreparedStatement ps = connection1.prepareStatement(sql)){
                ps.setInt(1,accountId);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Accounts acc = new Accounts();
                    acc.setAccountId(rs.getInt("account_id"));
                    acc.setCustomerId(rs.getInt("customer_id"));
                    acc.setBalance(rs.getDouble("balance"));
                    return acc;
                }else{
                    return null;
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
 
    /////////////////done
    @Override
    public void updateAccount(Accounts accounts)throws SQLException {
 
        String sql = "UPDATE accounts SET customer_id=?,balance=? where account_id=?";
 
        try(Connection connection1 = DatabaseConnectionManager.getConnection();PreparedStatement ps = connection1.prepareStatement(sql)){
            ps.setInt(1,accounts.getCustomerId());
            ps.setDouble(2, accounts.getBalance());
            ps.setInt(3, accounts.getAccountId());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }   
 
    //////////////not given in Day 3 still done
    @Override
    public void deleteAccount(int accountId)throws SQLException{
        String sql = "DELETE FROM accounts where account_id=?";
        Connection connection1 = DatabaseConnectionManager.getConnection();
        try(PreparedStatement ps = connection1.prepareStatement(sql)){
            ps.setInt(1,accountId);
            ps.executeUpdate();
        }catch(SQLException e){
                e.printStackTrace();
        }
    }
 
 
    /////////////////////////done
    @Override
    public List<Accounts> getAllAccounts() throws SQLException{
 
        List<Accounts> accounts = new ArrayList<>();
        String sql = "Select * from accounts";
 
        try(Connection connection1 = DatabaseConnectionManager.getConnection();PreparedStatement ps = connection1.prepareStatement(sql)){
 
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
                Accounts account = new Accounts(
                    rs.getInt("account_id"),
                    rs.getInt("customer_id"),
                    rs.getDouble("balance")
                );
                accounts.add(account);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
 
        return accounts;
    }
 
 
    ////////////////////done
    public List<Accounts> getAllAccountsByCustomers(int customer_id) throws SQLException{
        List<Accounts> accounts = new ArrayList<>();
        String sql = "Select * from accounts where customer_id=?";
 
        try(Connection connection1 = DatabaseConnectionManager.getConnection();PreparedStatement ps = connection1.prepareStatement(sql)){
            ps.setInt(1, customer_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Accounts account = new Accounts(
                    rs.getInt("account_id"),
                    rs.getInt("customer_id"),
                    rs.getDouble("salary")
                );
 
                accounts.add(account);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
 
        return accounts;
    }
 
    
}
