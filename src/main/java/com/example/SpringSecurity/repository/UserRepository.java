package com.example.SpringSecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringSecurity.model.User;

public interface UserRepository  extends JpaRepository<User,Integer>{
	
	Optional<User> findByEmail(String Email);

}
