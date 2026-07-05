package com.example.synora.repository;

import com.example.synora.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Productrepository extends JpaRepository<Product, Integer> {


    List<Product> findByNameContainingIgnoreCase(String name);


    List<Product> findByCategoryIgnoreCase(String category);
}