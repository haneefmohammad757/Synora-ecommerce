package com.example.synora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private String description;

    private String brand;

    private Double price;

    private Integer discount;

    private Integer quantity;

    private String category;

    private String imageUrl;

    private Boolean heroProduct;

    private Integer heroOrder;

    private Boolean dealProduct;

    public Product() {
    }

    public Product(String name, String description, double price, int quantity,
                   String imageUrl, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    // ID
    public Integer getId() {
        return id;
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Price
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Image URL
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Brand
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Discount
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    // Hero Product
    public Boolean getHeroProduct() {
        return heroProduct;
    }

    public void setHeroProduct(Boolean heroProduct) {
        this.heroProduct = heroProduct;
    }

    // Hero Order
    public Integer getHeroOrder() {
        return heroOrder;
    }

    public void setHeroOrder(Integer heroOrder) {
        this.heroOrder = heroOrder;
    }

    // Deal Product
    public Boolean getDealProduct() {
        return dealProduct;
    }

    public void setDealProduct(Boolean dealProduct) {
        this.dealProduct = dealProduct;
    }
}