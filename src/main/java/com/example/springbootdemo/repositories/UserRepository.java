package com.example.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootdemo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByName(String name);
}
