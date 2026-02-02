package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.edutech.progressive.dao.CustomerDAO;
import com.edutech.progressive.entity.Customers;

public class CustomerServiceImplJpa {

    private CustomerDAO customerDAO;

    public CustomerServiceImplJpa(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customers> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    public Customers getCustomerById(int customerId) throws SQLException {
        return customerDAO.getCustomerById(customerId);
    }

    public int addCustomer(Customers customers) throws SQLException {
        return customerDAO.addCustomer(customers);
    }

    public void updateCustomer(Customers customers) throws SQLException {
        customerDAO.updateCustomer(customers);
    }

    public void deleteCustomer(int customerId) throws SQLException {
        customerDAO.deleteCustomer(customerId);
    }

    public List<Customers> getAllCustomersSortedByName() throws SQLException {
        List<Customers> list = customerDAO.getAllCustomers();
        list.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        return list;
    }
}
