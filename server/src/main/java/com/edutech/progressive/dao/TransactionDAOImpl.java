package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Transactions;

public class TransactionDAOImpl implements TransactionDAO {

    private Connection connection;

    public TransactionDAOImpl() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addTransaction(Transactions transaction) throws SQLException {
        String sql = "INSERT INTO transactions(account_id, amount, type, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, transaction.getAccountId());
            ps.setDouble(2, transaction.getAmount());
            ps.setString(3, transaction.getTransactionType());
            if (transaction.getTransactionDate() != null) {
                ps.setDate(4, new Date(transaction.getTransactionDate().getTime()));
            } else {
                ps.setDate(4, null);
            }
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    transaction.setId(id);
                    return id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return transaction.getId();
    }

    @Override
    public Transactions getTransactionById(int id) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Transactions t = new Transactions();
                    t.setId(rs.getInt("id"));
                    t.setAccountId(rs.getInt("account_id"));
                    t.setAmount(rs.getDouble("amount"));
                    t.setTransactionType(rs.getString("type"));
                    Date d = rs.getDate("date");
                    if (d != null) {
                        t.setTransactionDate(new java.util.Date(d.getTime()));
                    }
                    return t;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    @Override
    public void updateTransaction(Transactions transaction) throws SQLException {
        String sql = "UPDATE transactions SET account_id = ?, amount = ?, type = ?, date = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, transaction.getAccountId());
            ps.setDouble(2, transaction.getAmount());
            ps.setString(3, transaction.getTransactionType());
            if (transaction.getTransactionDate() != null) {
                ps.setDate(4, new Date(transaction.getTransactionDate().getTime()));
            } else {
                ps.setDate(4, null);
            }
            ps.setInt(5, transaction.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTransaction(int id) throws SQLException {
        String sql = "DELETE FROM transactions WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
