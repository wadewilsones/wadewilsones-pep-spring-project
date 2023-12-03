package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;


@Service
public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository repo){
        this.messageRepository = repo;
    }

    public  ArrayList<Message> getMessages() {
        
        List<Message> messages = messageRepository.findAll();
        return (ArrayList<Message>) messages;
    }
}
