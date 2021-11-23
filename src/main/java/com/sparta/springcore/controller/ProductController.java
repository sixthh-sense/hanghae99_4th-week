package com.sparta.springcore.controller;

import com.sparta.springcore.model.Product;
import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

 // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    } // RequiredArgsConstructor로 대체할 수 있는 부분

    // 신규 상품 등록
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) throws SQLException {

        //로그인 되어있는 회원 table의 ID
        Long userId = userDetails.getUser().getId();
        Product product = productService.createProduct(requestDto, userId);

// 응답 보내기
        return product;
    }

    // 설정 가격 변경
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = productService.updateProduct(id, requestDto);

// 응답 보내기 (업데이트된 상품 id)
        return product.getId();
    }

    // 등록된 전체 상품 목록 조회
//    @GetMapping("/api/products")
//    public List<Product> getProducts() throws SQLException {
//        List<Product> products = productService.getProducts();
//
//// 응답 보내기
//        return products;
//    }

    //로그인한 회원이 등록한 관심 상품 조회
     @GetMapping("/api/products") // {id}
     public List<Product> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        //로그인 상태인 회원 table의 id 항목
         Long userId = userDetails.getUser().getId();

         return productService.getProducts(userId);
     }
}