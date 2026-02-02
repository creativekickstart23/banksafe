package com.edutech.progressive.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Transactions;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @GetMapping
    public List<Transactions> getAllTransactions() {
        return null;
    }

    @GetMapping("/{transactionId}")
    public Transactions getTransactionById(@PathVariable int transactionId) {
        return null;
    }

    @PostMapping
    public int addTransaction(@RequestBody Transactions transaction) {
        return -1;
    }

    @PutMapping("/{transactionId}")
    public void updateTransaction(@PathVariable int transactionId, @RequestBody Transactions transaction) {
    }

    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable int transactionId) {
    }
}
