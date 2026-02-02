package com.edutech.progressive.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Accounts;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping
    public List<Accounts> getAllAccounts() {
        return null;
    }

    @GetMapping("/{accountId}")
    public Accounts getAccountById(@PathVariable int accountId) {
        return null;
    }

    @GetMapping("/user/{param}")
    public List<Accounts> getAccountsByUser(@PathVariable String param) {
        return null;
    }

    @PostMapping
    public int addAccount(@RequestBody Accounts accounts) {
        return -1;
    }

    @PutMapping("/{accountId}")
    public void updateAccount(@PathVariable int accountId, @RequestBody Accounts accounts) {
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable int accountId) {
    }
}
