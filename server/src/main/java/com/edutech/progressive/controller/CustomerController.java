package com.edutech.progressive.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.service.impl.CustomerServiceImplArraylist;
import com.edutech.progressive.service.impl.CustomerServiceImplJpa;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImplArraylist arraylistService;
    private final CustomerServiceImplJpa jpaService;

    public CustomerController(CustomerServiceImplArraylist arraylistService,
                              CustomerServiceImplJpa jpaService) {
        this.arraylistService = arraylistService;
        this.jpaService = jpaService;
    }

    @GetMapping
    public List<Customers> getAllCustomers() throws SQLException {
        return jpaService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customers getCustomerById(@PathVariable int customerId) throws SQLException {
        return jpaService.getCustomerById(customerId);
    }

    @PostMapping
    public int addCustomer(@RequestBody Customers customers) throws SQLException {
        return jpaService.addCustomer(customers);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@PathVariable int customerId,
                               @RequestBody Customers customers) throws SQLException {
        customers.setCustomerId(customerId);
        jpaService.updateCustomer(customers);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable int customerId) throws SQLException {
        jpaService.deleteCustomer(customerId);
    }

    @GetMapping("/fromArrayList")
    public List<Customers> getAllCustomersFromArrayList() throws SQLException {
        return arraylistService.getAllCustomers();
    }

    @GetMapping("/fromArrayList/all")
    public List<Customers> getAllCustomersSortedByNameFromArrayList() throws SQLException {
        return arraylistService.getAllCustomersSortedByName();
    }

    @PostMapping("/toArrayList")
    @ResponseStatus(HttpStatus.CREATED)
    public int addCustomerToArrayList(@RequestBody Customers customers) throws SQLException {
        return arraylistService.addCustomer(customers);
    }
}
