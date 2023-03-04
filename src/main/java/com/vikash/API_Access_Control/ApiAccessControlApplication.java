package com.vikash.API_Access_Control;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vikash.API_Access_Control.Entity.Role;
import com.vikash.API_Access_Control.Entity.User;
import com.vikash.API_Access_Control.Repository.RoleRepository;
import com.vikash.API_Access_Control.Repository.UserRepository;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class ApiAccessControlApplication implements CommandLineRunner{

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
	public static void main(String[] args) {
		SpringApplication.run(ApiAccessControlApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		Role adminRole = new Role(1, "ADMIN");
		Role SubscriberRole = new Role(2, "Subscriber");
        Role NonSubscriberRole = new Role(3, "Non-Subscriber");
        roleRepository.saveAll(Arrays.asList(adminRole, SubscriberRole,NonSubscriberRole));
      
        User adminUser = new User(1, "adminname", "adminpassword", adminRole);
        User subscriberUser = new User(2, "subscribername", "subscriberpassword", SubscriberRole);
        User nonsubscriberUser = new User(3, "nonsubscribername", "nonsubscriberpassword", NonSubscriberRole);
        userRepository.saveAll(Arrays.asList(adminUser, subscriberUser, nonsubscriberUser));        
    } 

}
