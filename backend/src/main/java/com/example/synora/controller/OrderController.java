package com.example.synora.controller;

import com.example.synora.dto.CreateOrderResponse;
import com.example.synora.model.Order;
import com.example.synora.service.OrderService;
import com.example.synora.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/checkout")
    public CreateOrderResponse placeOrder(

            @RequestParam Integer userId

    ) {

        Order order = orderService.placeOrder(userId);

        return new CreateOrderResponse(

                order.getId(),

                order.getRazorpayOrderId(),

                (int)(order.getTotalAmount()*100),

                "INR",

                paymentService.getKey()

        );

    }

    @GetMapping("/{userId}")
    public List<Order> getOrders(

            @PathVariable Integer userId

    ) {

        return orderService.getUserOrders(userId);

    }

}