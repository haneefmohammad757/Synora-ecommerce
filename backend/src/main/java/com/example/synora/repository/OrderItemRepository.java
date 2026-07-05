package com.example.synora.repository;

import com.example.synora.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
}
