package com.example.synora.service;

import com.example.synora.model.*;
import jakarta.transaction.Transactional;

import com.example.synora.repository.CartItemRepository;
import com.example.synora.repository.CartRepository;
import com.example.synora.repository.Productrepository;
import com.example.synora.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
@Service
public class OrderService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private Productrepository productrepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;



    //checkout
    @Transactional
    public Order placeOrder(Integer userId){
        Cart cart =cartRepository.findByUserId(userId)
                .orElseThrow(()-> new RuntimeException("cart not found"));
        if(cart.getItems().isEmpty()){
            throw new RuntimeException(("cart is empty"));
        }
        Order order = new Order();
        order.setUserId(userId);
        order.setCartId(cart.getId());
        order.setStatus("PENDING");

        double total=0;
        for(CartItem cartItem:cart.getItems()){
            Product product=productrepository.findById(cartItem.getProductId())
                    .orElseThrow(()->new RuntimeException("product not found!"));
            //stock validation(hai ya nai items dekhna)
            if(product.getQuantity()<cartItem.getQuantity()){
                throw new RuntimeException("Insufficient stock for product:"+product.getName());
            }



            //create Order item
            // Create order item using product details at the time of purchase
            OrderItem orderItem = new OrderItem();

            orderItem.setProductId(product.getId());

            orderItem.setProductName(product.getName());

            orderItem.setImageUrl(product.getImageUrl());

            orderItem.setQuantity(cartItem.getQuantity());

            orderItem.setPrice(product.getPrice());

            orderItem.setOrder(order);
            order.getItems().add(orderItem);


            total+=product.getPrice()*cartItem.getQuantity();




        }
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        //create payment order
        try{
            org.json.JSONObject razorpayOrder=
                    paymentService.createRazorpayOrder(total);
            savedOrder.setRazorpayOrderId(razorpayOrder.getString("id"));
            orderRepository.save(savedOrder);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Payment creation failed: " + e.getMessage());
        }



// Reload the cart from database to ensure it's empty
        cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        System.out.println("Remaining Cart Items: " + cart.getItems().size());

        return savedOrder;
    }
    public java.util.List<Order> getUserOrders(Integer userId){
        return orderRepository.findByUserId(userId);
    }

}
