package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {


    //Inject Service
    private AccountService accountService;


    //Control user registration
    @PostMapping("/register")
    public ResponseEntity<Account> addUser(Account account){

       Account newAccount = accountService.addUser(account);   
       if(newAccount != null){
        return new ResponseEntity<>(newAccount, HttpStatus.OK);
       }
       else{
        return new ResponseEntity<>(HttpStatus.CONFLICT);
       }
    }

}
