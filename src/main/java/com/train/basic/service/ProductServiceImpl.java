package com.train.basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.train.basic.model.Product;
import com.train.basic.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;
    private final EmailService emailService;
    
    public ProductServiceImpl(ProductRepository repo, EmailService emailService) {
        this.repo = repo;
		this.emailService = emailService;
    }

  
    @Override
    public Product addProduct(Product product) {
    	System.out.println("product added...");
    	Product saved = repo.save(product);
    	String subject = "New Product Added!";
        String body = "Hello, a new product has been added to our store: " 
                      + saved.getName() 
                      + "\n\nDescription: " + saved.getDescription()
                      + "\nPrice: $" + saved.getPrice();
        System.out.println("Email sending started...");
        emailService.sendEmailToAllCustomers(subject, body);

        return saved;
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }
    
    @Override
    public Product updateProduct(Product updatedProduct) {
        Optional<Product> existingOpt = repo.findById(updatedProduct.getId());
        if (existingOpt.isPresent()) {
            Product existing = existingOpt.get();
            existing.setName(updatedProduct.getName());
            existing.setDescription(updatedProduct.getDescription());
            existing.setPrice(updatedProduct.getPrice());
            existing.setImage(updatedProduct.getImage());
            existing.setRating(updatedProduct.getRating());
            
            Product saved = repo.save(existing);

            // ✅ Send email notification
            String subject = "Product Updated: " + saved.getName();
            String body = "Hello, a product has been updated in our store: "
                          + saved.getName()
                          + "\n\nDescription: " + saved.getDescription()
                          + "\nPrice: $" + saved.getPrice();

            System.out.println("Email sending started for updated product...");
            emailService.sendEmailToAllCustomers(subject, body);

            return saved;
        } else {
            throw new RuntimeException("Product not found with id: " + updatedProduct.getId());
        }
    }

}