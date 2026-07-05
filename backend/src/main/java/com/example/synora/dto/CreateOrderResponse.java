package com.example.synora.dto;

public class CreateOrderResponse {

    private Integer appOrderId;

    private String razorpayOrderId;

    private int amount;

    private String currency;

    private String key;

    public CreateOrderResponse(
            Integer appOrderId,
            String razorpayOrderId,
            int amount,
            String currency,
            String key
    ) {

        this.appOrderId = appOrderId;

        this.razorpayOrderId = razorpayOrderId;

        this.amount = amount;

        this.currency = currency;

        this.key = key;

    }

    public Integer getAppOrderId() {

        return appOrderId;

    }

    public String getRazorpayOrderId() {

        return razorpayOrderId;

    }

    public int getAmount() {

        return amount;

    }

    public String getCurrency() {

        return currency;

    }

    public String getKey() {

        return key;

    }

}