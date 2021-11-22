package com.sparta.springcore;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
        ProductService productService = new ProductService();
        Product product = productService.createProduct(requestDto);
        return product;
    }

    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = new Product();

        return product.getId();
    }

    @GetMapping("/api/products")
    public List<Product> getProducts() throws SQLException {
        ProductService productService = new ProductService();
        List<Product> products = productService.getProducts();
        return products;
    }
}
