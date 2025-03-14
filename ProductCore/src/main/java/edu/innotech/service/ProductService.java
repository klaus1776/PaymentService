package edu.innotech.service;

import edu.innotech.dto.ProductDto;
import edu.innotech.entity.Product;
import edu.innotech.exceptions.NoDataFoundException;
import edu.innotech.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDto findProductById(Long id) {
        Product product =  productRepository.findProductById(id).
                orElseThrow(() -> new NoDataFoundException("Product not found", "NOT_FOUND"));
        return new ProductDto(Collections.singletonList(product));
    }

    public ProductDto findProductByAccount(String account) {
        Product product =  productRepository.findProductByAccount(account).
                orElseThrow(() -> new NoDataFoundException("Product not found", "NOT_FOUND"));
        return new ProductDto(Collections.singletonList(product));
    }

    public ProductDto findProductsByUserId(Long userId) {
        List<Product> products = productRepository.findProductsByUserId(userId).
                filter(e -> !e.isEmpty()).
                orElseThrow(() -> new NoDataFoundException("Products not found", "NOT_FOUND"));
        return new ProductDto(products);
    }

    public void setAmount(Long id, Double amount) {
        productRepository.updateProductAmount(id, amount);
    }
}
