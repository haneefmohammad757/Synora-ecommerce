package com.example.synora.service;

import com.example.synora.model.*;
import com.example.synora.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private Productrepository productrepository;

    //get or create cart
    public Cart getorCreateCart(Integer userId) {
        return cartRepository.findByUserId(userId).
                orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    return cartRepository.save(cart);
                });
    }
    public Cart addToCart(Integer userId ,Integer productId,int quantity) {
        Cart cart = getorCreateCart(userId);

        Product product = productrepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

// Check whether enough stock is available
        if(product.getQuantity() < quantity){

            throw new RuntimeException("Not enough stock available");

        }


        Optional<CartItem> existingItems = cart.getItems().stream().
                filter(item -> item.getProduct().getId().equals(productId)).
                findFirst();
        if(existingItems.isPresent()){
            CartItem item= existingItems.get();
            item.setQuantity(item.getQuantity()+quantity);
            cartItemRepository.save(item);
        }else{
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);


            cartItemRepository.save(newItem);
            cart.getItems().add(newItem);

        }
        return cartRepository.save(cart);
    }

    public Cart viewCart(Integer userId){
        return cartRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("Cart not found"));

    }
    //remove item
    public String removeItem(Integer itemId){
        cartItemRepository.deleteById(itemId);
        return "Item Removed";
    }
}
