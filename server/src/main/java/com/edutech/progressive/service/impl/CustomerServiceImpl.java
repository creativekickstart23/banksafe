package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.dao.CustomerDAO;
import com.edutech.progressive.dao.CustomerDAOImpl;
import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.service.CustomerService;

public class CustomerServiceImpl implements CustomerService  {

    private CustomerDAO customerDAO;
    
    public CustomerServiceImpl(CustomerDAOImpl customerDAO){
         this.customerDAO = customerDAO;
    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        // TODO Auto-generated method stub
        // List<Customers> list = new ArrayList<>();
        // return list;
        return customerDAO.getAllCustomers();
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        // TODO Auto-generated method stub
        // return -1;
        // return customerDAO.addCustomer(customers);
        int generatedId = customerDAO.addCustomer(customers);
        customers.setCustomerId(generatedId);
        return generatedId;
    }

    @Override
    public List<Customers> getAllCustomersSortedByName() throws SQLException {
        // TODO Auto-generated method stub
        // List<Customers> list = new ArrayList<>();
        // return list;
        List<Customers> list = customerDAO.getAllCustomers();
        list.sort((a,b) -> a.getName().compareToIgnoreCase(b.getName()));
        return list;
     }

    public void updateCustomer(Customers customers) throws SQLException{
            customerDAO.updateCustomer(customers);
    }
    public void deleteCustomer(int customerId)throws SQLException{
           customerDAO.deleteCustomer(customerId);
    }
    public Customers getCustomerById(int customerId) throws SQLException{
          return customerDAO.getCustomerById(customerId);
    }

}
