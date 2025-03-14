package edu.innotech.controller;

import edu.innotech.dto.ProductDto;
import edu.innotech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/product")
    public ProductDto findProductById(@RequestParam("id") Long productId) {
        return productService.findProductById(productId);
    }

    @GetMapping(value = "/account")
    public ProductDto findProductByAccount(@RequestParam("num") String account) {
        return productService.findProductByAccount(account);
    }

    @GetMapping(value = "/user")
    public ProductDto findProductsByUserId(@RequestParam("id") Long userId) {
        ProductDto products = productService.findProductsByUserId(userId);
        return products;
    }

    @PostMapping(value = "/payment")
    public void setAmount(@RequestParam("id") Long productId, @RequestParam("amount") Double amount) {
        productService.setAmount(productId, amount);
    }
}
