package com.eb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eb.Entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUserId(String userId);
   
}