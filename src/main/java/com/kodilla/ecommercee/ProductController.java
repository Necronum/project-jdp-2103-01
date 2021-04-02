package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.http.MediaType;
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
    public ProductDto getProduct(@RequestParam Long id) {
        return new ProductDto(1L, "Test product");
    }

    @PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {

    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(1L, "Updated test product");
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long id) {

    }
}
