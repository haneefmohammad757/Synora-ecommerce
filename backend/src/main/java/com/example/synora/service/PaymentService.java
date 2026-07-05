package com.example.synora.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    public JSONObject createRazorpayOrder(double amount)
            throws RazorpayException {

        RazorpayClient client =
                new RazorpayClient(key, secret);

        JSONObject options = new JSONObject();

        options.put("amount", (int)(amount * 100));

        options.put("currency", "INR");

        options.put("receipt",
                "ORDER_" + System.currentTimeMillis());

        Order order = client.orders.create(options);

        return order.toJson();
    }

    public String getKey() {

        return key;

    }

    public boolean verifyPayment(

            String razorpayOrderId,

            String razorpayPaymentId,

            String razorpaySignature

    ) {

        try {

            JSONObject options = new JSONObject();

            options.put("razorpay_order_id",
                    razorpayOrderId);

            options.put("razorpay_payment_id",
                    razorpayPaymentId);

            options.put("razorpay_signature",
                    razorpaySignature);

            return Utils.verifyPaymentSignature(
                    options,
                    secret
            );

        }

        catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

}