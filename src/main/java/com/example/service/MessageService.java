package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;


@Service
public class MessageService {

    MessageRepository messageRepository;
    AccountRepository accountRepo;

    @Autowired
    public MessageService(MessageRepository repo,  AccountRepository accountRepo){
        this.messageRepository = repo;
        this.accountRepo = accountRepo;
    }

    public  ArrayList<Message> getMessages() {
        
        List<Message> messages = messageRepository.findAll();
        return (ArrayList<Message>) messages;
    }

    public Message postMessage(Message message) {

        if(validateInput(message)){
            Message newMessage = messageRepository.save(message);
            return newMessage;
        }
        
        return null;
    }



    public boolean validateInput(Message message){

        if (message.getMessage_text().length() < 255 & message.getMessage_text() != ""){
            
            Optional <Account> user = accountRepo.findById(message.getPosted_by());
            if(user.isPresent()){
                return true;
            }
        }

        return false;
    }
}
