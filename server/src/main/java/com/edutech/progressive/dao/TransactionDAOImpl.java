package com.edutech.progressive.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import com.edutech.progressive.config.DatabaseConnectionManager;
 
import com.edutech.progressive.entity.Transactions;
 
public class TransactionDAOImpl implements TransactionDAO{
 
    private Connection connection;
 
    public TransactionDAOImpl(){
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public int addTransaction(Transactions transaction) throws SQLException{
        String sql ="INSERT INTO transactions(account_id,amount,transaction_date,transaction_type) VALUES(?,?,?,?)";
 
        try(PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,transaction.getAccountId());
            ps.setDouble(2,transaction.getAmount());
 
            java.util.Date utilDate = transaction.getTransactionDate();
 
            ps.setDate(3, new java.sql.Date(utilDate.getTime()));
            ps.setString(4,transaction.getTransactionType());
            ps.executeUpdate();
 
            ResultSet rs = ps.getGeneratedKeys();
 
            if(rs.next()){
                transaction.setTransactionId(rs.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return transaction.getTransactionId();
    }
 
    @Override
    public Transactions getTransactionById(int transactionId)throws SQLException {
        Transactions transactions = null;
        String sql = "Select * from transactions where transaction_id=?";
 
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,transactionId);
 
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                transactions = new Transactions(rs.getInt("transaction_id"), rs.getInt("account_id"), rs.getDouble("amount"), rs.getDate("transaction_date"), rs.getString("transaction_type"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void updateTransaction(Transactions transaction) throws SQLException{
        String sql = "UPDATE transactions set amount=?,transaction_date=?,transaction_type=? where transaction_id=?";
 
        try(PreparedStatement ps = connection.prepareStatement(sql)){
                //ps.setInt(1, transaction.getAccountId());
                ps.setDouble(1, transaction.getAmount());
                java.util.Date utilDate = transaction.getTransactionDate();
                ps.setDate(2, new java.sql.Date(utilDate.getTime()));
                ps.setString(3, transaction.getTransactionType());
                ps.setInt(4,transaction.getTransactionId());
                ps.executeUpdate();
        }catch(SQLException e){
                e.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(int transactionId) throws SQLException{
        String sql = "DELETE from transactions where transaction_id=?";
 
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,transactionId);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
 
    @Override
    public List<Transactions> getAllTransactions() throws SQLException {
        List<Transactions> transactionList = new ArrayList<>();
 
        String sql = "Select * from transactions";
 
        try(PreparedStatement ps = connection.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
 
                while(rs.next()){
                    Transactions transaction = new Transactions(rs.getInt("transaction_id"), rs.getInt("account_id"), rs.getDouble("amount"), rs.getDate("transaction_date"), rs.getString("transaction_type"));
                    transactionList.add(transaction);
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return transactionList;
    }
 
    
}
