package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.dao.CustomerDAO;
import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{

    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        return -1;
    }

    @Override
    public List<Customers> getAllCustomersSortedByName() throws SQLException {
        List<Customers> sortedList=new ArrayList<>();
        return sortedList;
    }
    public void updateCustomer(Customers customers) throws SQLException {

    }
    public void deleteCustomer(int customerId) throws SQLException {

    }
    public Customers getCustomerById(int customerId) throws SQLException {
        return null;
    }

}