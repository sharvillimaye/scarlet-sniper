package com.sharvillimaye.backend;

import com.sharvillimaye.backend.model.Role;
import com.sharvillimaye.backend.model.User;
import com.sharvillimaye.backend.dao.RoleDAO;
import com.sharvillimaye.backend.dao.UserDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleDAO roleDAO, UserDAO userDAO, PasswordEncoder passwordEncode){
		return args ->{
			if(roleDAO.findByAuthority("ADMIN").isPresent()) {
				return;
			}

			Role adminRole = roleDAO.save(new Role("ADMIN"));
			roleDAO.save(new Role("USER"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			User admin = new User(1, "admin", passwordEncode.encode("password"), roles);

			userDAO.save(admin);
		};
	}
}