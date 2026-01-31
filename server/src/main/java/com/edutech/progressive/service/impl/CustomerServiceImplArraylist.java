package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.service.CustomerService;

public class CustomerServiceImplArraylist implements CustomerService {
    public static List<Customers> customersList=new ArrayList<>();

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        return customersList;
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        customersList.add(customers);
        int s=customersList.size();
        return s;
    }

    @Override
    public List<Customers> getAllCustomersSortedByName() throws SQLException {
        Collections.sort(customersList);
        return customersList;
    }

    public void emptyArrayList(){
        customersList.clear();
    }

}