package com.example.synora.service;

import com.example.synora.exception.ResourceNotFoundException;
import com.example.synora.model.Product;
import com.example.synora.repository.Productrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private Productrepository productrepository;

    // add a new product
    public Product addProduct(Product product) {
        return productrepository.save(product);
    }

    // get all products
    public List<Product> getAllProducts() {
        return productrepository.findAll();
    }

    // get one product using id
    public Product getProductById(Integer id) {

        return productrepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id : " + id));
    }

    // update product details
    // update product details
    public Product updateProduct(Integer id, Product newProduct) {

        Product existing = productrepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + id));

        existing.setName(newProduct.getName());

        existing.setDescription(newProduct.getDescription());

        existing.setBrand(newProduct.getBrand());

        existing.setPrice(newProduct.getPrice());

        existing.setDiscount(newProduct.getDiscount());

        existing.setQuantity(newProduct.getQuantity());

        existing.setCategory(newProduct.getCategory());

        existing.setImageUrl(newProduct.getImageUrl());

        existing.setHeroProduct(newProduct.getHeroProduct());

        existing.setHeroOrder(newProduct.getHeroOrder());

        existing.setDealProduct(newProduct.getDealProduct());

        return productrepository.save(existing);

    }

    // delete product
    public void deleteProduct(Integer id) {

        Product product = productrepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id : " + id));

        productrepository.delete(product);
    }
}