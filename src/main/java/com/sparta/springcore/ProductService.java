package com.sparta.springcore;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    public Product createProduct(ProductRequestDto requestDto) throws SQLException {
    Product product = new Product(requestDto);

    ProductRepository productRepository = new ProductRepository();
    productRepository.createProduct(product);
    return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.getProduct(id);
        if (requestDto.getMyprice() <=0) {
            throw new RuntimeException("최저가를 0 이상으로 입력하십시오.");
        }

        int myprice = requestDto.getMyprice();
        productRepository.updateMyprice(id, myprice);
        return product;
    }

    public List<Product> getProducts() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.getProducts();

        return products;
    }

}
