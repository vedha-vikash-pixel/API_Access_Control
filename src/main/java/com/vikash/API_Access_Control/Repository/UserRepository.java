package com.vikash.API_Access_Control.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikash.API_Access_Control.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {	

	Optional<User> findByUsername(String username);
	
}
