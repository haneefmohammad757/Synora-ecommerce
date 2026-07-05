package com.example.synora.repository;

import com.example.synora.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findByUserId(Integer userId);

    Optional<Order> findByRazorpayOrderId(String razorpayOrderId);

}