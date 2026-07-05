package com.example.synora.service;

import com.example.synora.model.Cart;
import com.example.synora.model.Order;
import com.example.synora.model.OrderItem;
import com.example.synora.model.Product;
import com.example.synora.repository.CartItemRepository;
import com.example.synora.repository.CartRepository;
import com.example.synora.repository.OrderRepository;
import com.example.synora.repository.Productrepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaymentVerificationService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Productrepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public String verifyPayment(

            String razorpayOrderId,

            String razorpayPaymentId,

            String razorpaySignature

    ) {

        boolean verified = paymentService.verifyPayment(

                razorpayOrderId,

                razorpayPaymentId,

                razorpaySignature

        );

        if (!verified) {

            throw new RuntimeException("Payment Verification Failed");

        }

        Order order = orderRepository

                .findByRazorpayOrderId(razorpayOrderId)

                .orElseThrow(() ->

                        new RuntimeException("Order Not Found"));



        /*
         * Reduce Stock
         */

        for (OrderItem item : order.getItems()) {

            Product product = productRepository

                    .findById(item.getProductId())

                    .orElseThrow(() ->

                            new RuntimeException(

                                    "Product Not Found"

                            ));

            if (product.getQuantity() < item.getQuantity()) {

                throw new RuntimeException(

                        "Insufficient Stock : "

                                + product.getName()

                );

            }

            product.setQuantity(

                    product.getQuantity()

                            - item.getQuantity()

            );

            productRepository.save(product);

        }



        /*
         * Clear Cart
         */

        Cart cart = cartRepository

                .findById(order.getCartId())

                .orElseThrow(() ->

                        new RuntimeException("Cart Not Found"));



        cartItemRepository.deleteByCartId(cart.getId());



        cart.getItems().clear();

        cartRepository.save(cart);



        /*
         * Update Order
         */

        order.setPaymentId(

                razorpayPaymentId

        );



        order.setStatus("PAID");



        orderRepository.save(order);



        return "Payment Verified Successfully";

    }

}