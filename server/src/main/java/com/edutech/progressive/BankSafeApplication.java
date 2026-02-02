package com.edutech.progressive;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
import com.edutech.progressive.dao.AccountDAO;
import com.edutech.progressive.dao.AccountDAOImpl;
import com.edutech.progressive.entity.Accounts;
@SpringBootApplication
public class BankSafeApplication {
 
    

    public static void main(String[] args) throws Exception{
        System.out.println("Welcome to BankSafe Project!");
        AccountDAO accountDAO = new AccountDAOImpl();
 
        //List<Accounts> accList = new ArrayList<>();
        Accounts acc = new Accounts();
        acc.setCustomerId(101);
        acc.setBalance(5000);
 
        int accountId = accountDAO.addAccount(acc);
 
        System.out.println("Inserted Account ID: " + accountId);
 
        Accounts inserted = accountDAO.getAccountById(accountId);
        System.out.println("After insert: "+ inserted);
 
        accountDAO.deleteAccount(accountId);
        System.out.println("Account deleted");
 
        Accounts deleted = accountDAO.getAccountById(accountId);
        System.out.println("After delete: " + deleted);
 
        

 
        
    }
}
