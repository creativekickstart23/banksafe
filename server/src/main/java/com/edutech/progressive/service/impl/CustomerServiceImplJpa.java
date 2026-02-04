// package com.edutech.progressive.service.impl;

// import java.sql.SQLException;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.edutech.progressive.dao.CustomerDAO;
// import com.edutech.progressive.entity.Customers;
// import com.edutech.progressive.repository.CustomerRepository;

// @Service
// public class CustomerServiceImplJpa {

//     @Autowired
//     private CustomerRepository custRepo;
//     public CustomerServiceImplJpa(CustomerRepository custRepo) {
//         this.custRepo = custRepo;
//     }

//     public List<Customers> getAllCustomers() throws SQLException {
//         return custRepo.findAll();
//     }

//     public Customers getCustomerById(int customerId) throws SQLException {
//         return custRepo.findByCustomerId(customerId);
//     }

//     public int addCustomer(Customers customers) throws SQLException {
//         return custRepo.save(customers).getCustomerId();
//     }

//     public void updateCustomer(Customers customers) throws SQLException {
//         custRepo.save(customers);
//     }

//     public void deleteCustomer(int customerId) throws SQLException {
//         custRepo.deleteByCustomerId(customerId);
//     }

//     public List<Customers> getAllCustomersSortedByName() throws SQLException {
//         List<Customers> list = custRepo.findAll();
//         list.sort(Customers::compareTo);
//         return list;
//     }
// }


package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.repository.CustomerRepository;
import com.edutech.progressive.service.CustomerService;

@Service
public class CustomerServiceImplJpa implements CustomerService{

    private final CustomerRepository custRepo;
    public CustomerServiceImplJpa(CustomerRepository custRepo) {
        this.custRepo = custRepo;
    }
    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        return custRepo.findAll();
    }
    @Override
    public Customers getCustomerById(int customerId) throws SQLException {
        return custRepo.findByCustomerId(customerId);
    }
    @Override
    public int addCustomer(Customers customers) throws SQLException {
        return custRepo.save(customers).getCustomerId();
    }
    @Override
    public void updateCustomer(Customers customers) throws SQLException {
        custRepo.save(customers);
    }
    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        custRepo.deleteByCustomerId(customerId);
    }
    @Override
    public List<Customers> getAllCustomersSortedByName() throws SQLException {
        List<Customers> list = custRepo.findAll();
        list.sort(Customers::compareTo);
        return list;
    }
}