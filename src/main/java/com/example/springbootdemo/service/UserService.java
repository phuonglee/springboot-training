package com.example.springbootdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.springbootdemo.model.User;

public interface UserService {
	User findById(Long id);
	 
    User findByName(String name);
 
    void saveUser(User user);
 
    void updateUser(User user);
 
    void deleteUserById(Long id);
 
    void deleteAllUsers();
 
    List<User> findAllUsers();
 
    boolean isUserExist(User user);
    
    Page<User> findPaginated(int page, int size);
}
