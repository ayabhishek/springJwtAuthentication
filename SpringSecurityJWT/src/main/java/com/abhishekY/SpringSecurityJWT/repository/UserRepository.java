package com.abhishekY.SpringSecurityJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishekY.SpringSecurityJWT.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String Email);
	// here findBy is from JPA but you can use any attribute from the user or your model class 
	//and create a custom findBy method

}
