package com.example.synora.repository;

import com.example.synora.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<user, Integer> {

     // find user using username
     Optional<user> findByUsername(String username);

     // check whether username already exists
     boolean existsByUsername(String username);
}