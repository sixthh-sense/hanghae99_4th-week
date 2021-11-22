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
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
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
