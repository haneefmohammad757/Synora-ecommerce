package com.example.synora.controller;

import com.example.synora.dto.PaymentVerifyRequest;
import com.example.synora.service.PaymentVerificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentVerificationService paymentVerificationService;

    @PostMapping("/verify")
    public String verifyPayment(
            @RequestBody PaymentVerifyRequest request
    ) {

        return paymentVerificationService.verifyPayment(

                request.getRazorpay_order_id(),

                request.getRazorpay_payment_id(),

                request.getRazorpay_signature()

        );

    }

}