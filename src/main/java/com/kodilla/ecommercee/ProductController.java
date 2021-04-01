package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }
    @GetMapping(value = "getProduct")
    public ProductDto getProduct (Long id) {
        return new ProductDto(1L, "Test product");
    }
    @PostMapping("createProduct")
    public void createProduct (ProductDto productDto) {

    }
    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct (ProductDto productDto) {
        return new ProductDto(1L, "Updated test product");
    }

    public void deleteProduct (Long id) {

    }
}
