package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/v1/cart")
public class CartController {
    @PostMapping(value="createEmptyCart")
    public List<ProductDto> createEmptyCart(){
        return new ArrayList<>();
    }

    @GetMapping(value="getCart")
    public List<ProductDto> getCart(){
        return new ArrayList<>();
    }

    @PutMapping(value="addProductToCart")
    public void addProductToCart(@RequestBody ProductDto productDto){
        //do nothing
    }

    @DeleteMapping(value="deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam Long productId){
        //do nothing
    }

    @PostMapping(value="createOrder")
    public void createOrder(@RequestBody List<ProductDto> cart){
        //do nothing
    }
}
