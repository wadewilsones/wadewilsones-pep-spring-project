package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {

    //Inject Service
    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService service, MessageService mService){
        this.accountService = service;
        this.messageService = mService;

    }

    //User registration
    @PostMapping("/register")
    public ResponseEntity<Account> addUser(@RequestBody Account account){

       Account newAccount = accountService.addUser(account); 
        //return null;
        if(newAccount != null){
        return ResponseEntity.ok(account);
       }
       else{
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
       }
           
    }

    //User Login
    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account){

        Account user = accountService.loginUser(account);

        if(user != null){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
      
    }

    //Retrieve all messages
    @GetMapping("/messages")
    public ResponseEntity<ArrayList<Message>> getMessages(){

        ArrayList<Message> messages = messageService.getMessages();
        return ResponseEntity.ok(messages);
    }

    //Post a new message
    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message){

        Message postedMessage = messageService.postMessage(message);
        if(postedMessage != null){
            return ResponseEntity.ok(postedMessage);
        }
        else{
            return ResponseEntity.status(400).build();
        }
       
    }

    //Get a message byId

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int message_id){

        Message requestedMessage = messageService.getMessageById(message_id);
        return ResponseEntity.ok(requestedMessage);
    }


    /**
     * As a User, I should be able to submit a DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}.

- The deletion of an existing message should remove an existing message from the database. If the message existed, the response body should contain the number of rows updated (1). The response status should be 200, which is the default.
- If the message did not exist, the response status should be 200, but the response body should be empty. This is because the DELETE verb is intended to be idempotent, ie, multiple calls to the DELETE endpoint should respond with the same type of response.

     */
@DeleteMapping("/messages/{message_id}")
public ResponseEntity<String> deleteMessage(@PathVariable int message_id){
   String response =  messageService.deleteMessage(message_id);
   return ResponseEntity.ok(response);
}
}