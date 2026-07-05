package com.example.synora.repository;

import com.example.synora.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository
        extends JpaRepository<Wishlist,Integer> {

    List<Wishlist> findByUserId(Integer userId);

    Optional<Wishlist> findByUserIdAndProduct_Id(

            Integer userId,

            Integer productId

    );

}