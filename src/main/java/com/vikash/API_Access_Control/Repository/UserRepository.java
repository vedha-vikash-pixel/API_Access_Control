package com.vikash.API_Access_Control.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikash.API_Access_Control.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {	 // user repository for User persistance operations

	Optional<User> findByUsername(String username);   //abstract method to find data in db by user name
	
}
