package com.vikash.API_Access_Control;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vikash.API_Access_Control.Entity.User;
import com.vikash.API_Access_Control.Repository.UserRepository;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class ApiAccessControlApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(ApiAccessControlApplication.class, args);
	}

}
