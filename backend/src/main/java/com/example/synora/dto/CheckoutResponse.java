package com.example.synora.dto;

public class CheckoutResponse {

    private Integer orderId;
    private String razorpayOrderId;
    private int amount;
    private String currency;
    private String key;

    public CheckoutResponse(Integer orderId,
                            String razorpayOrderId,
                            int amount,
                            String currency,
                            String key) {

        this.orderId = orderId;
        this.razorpayOrderId = razorpayOrderId;
        this.amount = amount;
        this.currency = currency;
        this.key = key;
    }

    public Integer getOrderId() {
        return orderId;
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