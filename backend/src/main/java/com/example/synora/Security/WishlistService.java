package com.example.synora.service;

import com.example.synora.model.Product;
import com.example.synora.model.Wishlist;
import com.example.synora.repository.Productrepository;
import com.example.synora.repository.WishlistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private Productrepository productRepository;

    // =============================
    // Add Product to Wishlist
    // =============================
    public Wishlist addToWishlist(

            Integer userId,

            Integer productId

    ) {

        wishlistRepository

                .findByUserIdAndProduct_Id(

                        userId,

                        productId

                )

                .ifPresent(item -> {

                    throw new RuntimeException(

                            "Product already exists in wishlist."

                    );

                });

        Product product = productRepository

                .findById(productId)

                .orElseThrow(() ->

                        new RuntimeException(

                                "Product not found."

                        ));

        Wishlist wishlist = new Wishlist();

        wishlist.setUserId(userId);

        wishlist.setProduct(product);

        return wishlistRepository.save(wishlist);

    }

    // =============================
    // Get User Wishlist
    // =============================
    public List<Wishlist> getWishlist(

            Integer userId

    ) {

        return wishlistRepository.findByUserId(

                userId

        );

    }

    // =============================
    // Remove Product
    // =============================
    public void removeProduct(

            Integer wishlistId

    ) {

        wishlistRepository.deleteById(

                wishlistId

        );

    }

}