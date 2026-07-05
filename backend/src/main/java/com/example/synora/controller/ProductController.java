package com.example.synora.controller;

import com.example.synora.model.Product;
import com.example.synora.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ===========================
    // CREATE PRODUCT (Admin)
    // ===========================
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // ===========================
    // GET ALL PRODUCTS
    // ===========================
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // ===========================
    // GET PRODUCT BY ID
    // ===========================
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    // ===========================
    // UPDATE PRODUCT (Admin)
    // ===========================
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id,
                                 @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    // ===========================
    // DELETE PRODUCT (Admin)
    // ===========================
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {

        productService.deleteProduct(id);

        return "Product Deleted Successfully";
    }
}