package com.example.service;

import java.util.Optional;

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
    public String addUser(Account account) {

        if(accountRepository.findByUsername(account.getUsername()) != null){
            return "409";
        }
        else{
            if(validateInput(account)){
                accountRepository.save(account);
                return "200";
            }
            else{
                return "400";
            }
        }
        
    }


    public Account loginUser(Account account) {

        Account existingAccount =  accountRepository.findByUsername(account.getUsername());

       if(existingAccount != null){
            if(existingAccount.getPassword().equals(account.getPassword())){
                return existingAccount;
            }
            else{
                return null;
            }
           
        }
        else{
            return null;
        }
       
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
