package com.train.basic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train.basic.model.Product;
import com.train.basic.service.ProductService;
import com.train.basic.service.EmailService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173") // frontend React port
public class ProductController {

    private final ProductService service;
    private final EmailService emailService;
    
    public ProductController(ProductService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService; // assign properly
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id); // ensure ID is set
        return service.updateProduct(product); // email will be sent inside service
    }

}
