package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    //Inject Repository
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository repo){
        this.accountRepository = repo;
    }

    //Add a new account
    public Account addUser(Account account) {
        
        if(validateInput(account)){
            return accountRepository.save(account);
        }
        
        return null;

    }


    public boolean validateInput(Account account){
        if(account.getUsername().length() > 0 & account.getUsername() != " " & account.getPassword().length() > 4){
            if(accountRepository.findByUsername(account.getUsername()) == null){
                return true;
            }
            else{
                return false;
            }          
        }
        else{
            return false;
        }
    }
}
