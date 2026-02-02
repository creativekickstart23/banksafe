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

public class CustomerDAOImpl implements CustomerDAO {

    private Connection connection;

    public CustomerDAOImpl() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        String sql = "INSERT INTO customers(name, email, username, password, role) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, customers.getName());
            ps.setString(2, customers.getEmail());
            ps.setString(3, customers.getUsername());
            ps.setString(4, customers.getPassword());
            ps.setString(5, customers.getRole());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    customers.setCustomerId(generatedId);
                    return generatedId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return customers.getCustomerId();
    }

    @Override
    public Customers getCustomerById(int customerId) throws SQLException {
        Customers customer = null;
        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customers(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                    customer.setRole(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return customer;
    }

    @Override
    public void updateCustomer(Customers customers) throws SQLException {
        String sql = "UPDATE customers SET name = ?, email = ?, username = ?, password = ?, role = ? WHERE customer_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customers.getName());
            ps.setString(2, customers.getEmail());
            ps.setString(3, customers.getUsername());
            ps.setString(4, customers.getPassword());
            ps.setString(5, customers.getRole());
            ps.setInt(6, customers.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        List<Customers> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customers customer = new Customers(
                    rs.getInt("customer_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("password")
                );
                customer.setRole(rs.getString("role"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return customerList;
    }

    @Override
    public CustomerAccountInfo getCustomerAccountInfo(int customerId) throws SQLException {
        String sql = "SELECT c.customer_id, c.name, c.email, a.account_id, a.balance " +
                     "FROM customers c JOIN accounts a ON c.customer_id = a.customer_id " +
                     "WHERE c.customer_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CustomerAccountInfo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDouble(5)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }
}
