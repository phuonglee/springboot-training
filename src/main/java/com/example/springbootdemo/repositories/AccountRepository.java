package com.example.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootdemo.model.Account;
import com.example.springbootdemo.model.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findByUserName(String userName);
	
	Account findByUserNameAndPassword(String userName, String password);
}
