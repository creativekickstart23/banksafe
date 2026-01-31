package com.edutech.progressive.dao;

import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.dto.CustomerAccountInfo;
import com.edutech.progressive.entity.Customers;

public class CustomerDAOImpl implements CustomerDAO{

    List<Customers> customersList=new ArrayList<>();
    @Override
    public int addCustomer(Customers customers) {
        return -1;
    }

    @Override
    public Customers getCustomerById(int customerId) {
        return null;
    }

    @Override
    public void updateCustomer(Customers customers) {

    }

    @Override
    public void deleteCustomer(int customerId) {

    }

    @Override
    public List<Customers> getAllCustomers() {
        return customersList;
    }

    @Override
    public CustomerAccountInfo getCustomerAccountInfo(int customerId) {
        return null;
    }

}
