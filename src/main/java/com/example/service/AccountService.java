package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

public class AccountService {

    //Inject Repository
    private AccountRepository accountRepository;

    public Account addUser(Account account) {

        //Validate

        if(validateInput(account)){
            //Perform search for duplicates

            if(accountRepository.findByUsername(account.getUsername()) == null){
                Account newAccount = accountRepository.addUser();
                return newAccount;
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

            return true;
            
        }
        else{
            return false;
        }
    }
}
