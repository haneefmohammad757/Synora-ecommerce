package com.example.synora.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;
    private int quantity;
    @ManyToOne
    @JoinColumn(name="cart_id")
    @JsonIgnore
    private Cart cart;
    public CartItem(){

    }
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    public Integer getId(){return id;}

    public Integer getProductId(){return productId;}

    public void setProductId(Integer productId){
        this.productId=productId;

    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity=quantity;}

    public Cart getCart(){
        return cart;

    }
    public void setCart(Cart cart){
        this.cart=cart;
    }


}
