package com.example.synora.config;

import com.example.synora.model.user;
import com.example.synora.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner createAdmin(

            UserRepository userRepository,

            BCryptPasswordEncoder encoder

    ) {

        return args -> {

            String adminUsername = "dracorex";

            if (!userRepository.existsByUsername(adminUsername)) {

                user admin = new user();

                admin.setUsername(adminUsername);

                admin.setPassword(

                        encoder.encode("Beru777")

                );

                admin.setRole("ADMIN");

                userRepository.save(admin);

                System.out.println("===================================");

                System.out.println(" Admin Account Created Successfully ");

                System.out.println(" Username : " + adminUsername);

                System.out.println("===================================");

            }

        };

    }

}