package com.edutech.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Integer> {

    public Customers findByCustomerId(int customerId);
    public void deleteByCustomerId(int customerId);
}
