package com.foxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import com.foxy.config.CustomUserDetails;
import com.foxy.repository.sql.UserRepository;

@SpringBootApplication
public class FoxyApiApp {

	public static void main(String[] args) {
		SpringApplication.run(FoxyApiApp.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepository) throws Exception{
		builder.userDetailsService(s -> new CustomUserDetails(userRepository.findByUsername(s)));
	}
}
