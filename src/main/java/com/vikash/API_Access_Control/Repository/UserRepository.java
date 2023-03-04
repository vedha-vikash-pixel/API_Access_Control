package com.vikash.API_Access_Control.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikash.API_Access_Control.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {	

	User findByUsername(String username);
	
}
