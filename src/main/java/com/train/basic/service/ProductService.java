package com.train.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.train.basic.model.Product;


@Service
public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
	Product updateProduct(Product updatedProduct);
}
