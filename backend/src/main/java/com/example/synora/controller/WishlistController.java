package com.example.synora.controller;

import com.example.synora.model.Wishlist;
import com.example.synora.service.WishlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // ==========================
    // Add Product to Wishlist
    // ==========================

    @PostMapping("/add")
    public Wishlist addToWishlist(

            @RequestParam Integer userId,

            @RequestParam Integer productId

    ) {

        return wishlistService.addToWishlist(

                userId,

                productId

        );

    }

    // ==========================
    // Get User Wishlist
    // ==========================

    @GetMapping("/{userId}")
    public List<Wishlist> getWishlist(

            @PathVariable Integer userId

    ) {

        return wishlistService.getWishlist(

                userId

        );

    }

    // ==========================
    // Remove Wishlist Item
    // ==========================

    @DeleteMapping("/{wishlistId}")
    public String removeProduct(

            @PathVariable Integer wishlistId

    ) {

        wishlistService.removeProduct(

                wishlistId

        );

        return "Product removed from wishlist.";

    }

}