package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="/v1/cart")
public class CartController {
    @PostMapping(value="createEmptyCart")
    public CartDto createEmptyCart(){
        return new CartDto();
    }

    @GetMapping(value="getCart")
    public CartDto getCart(@RequestParam Long cartId){
        return new CartDto(1L);
    }

    @PutMapping(value="addProductToCart")
    public void addProductToCart(@RequestParam Long cartId, @RequestParam Long productId){
        //do nothing
    }

    @DeleteMapping(value="deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam Long cartId, @RequestParam Long productId){
        //do nothing
    }

    @PostMapping(value="createOrder")
    public void createOrder(@RequestParam Long cartId){
        //do nothing
    }
}
