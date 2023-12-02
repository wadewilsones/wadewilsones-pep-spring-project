package com.example.repository;


import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository {

    public Account addUser();

    public Account findByUsername(String username);
}
