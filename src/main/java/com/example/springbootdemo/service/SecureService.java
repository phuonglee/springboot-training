package com.example.springbootdemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.springbootdemo.model.Account;

public interface SecureService extends UserDetailsService {
    Account findByUserNameAndPassword(String userName, String password);
}
