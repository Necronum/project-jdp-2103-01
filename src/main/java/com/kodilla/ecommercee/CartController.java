package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value="/v1/cart")
public class CartController {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CartDto createEmptyCart(){
        return new CartDto();
    }

    @GetMapping("/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartDto getCart(@PathVariable Long cartId){
        return new CartDto(1L);
    }

    @PutMapping("/{cartId}?{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addProductToCart(@PathVariable Long cartId, @PathVariable Long productId){
        log.info("Product: " + productId + " has been added to cart: " + cartId);
    }

    @DeleteMapping("/{cartId}?{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId){
        log.info("Product: " + productId + " has been removed to cart: " + cartId);
    }

    @PostMapping("/{cartId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@PathVariable Long cartId){
        log.info("Order form cart: " + cartId + " has been added to car ");
    }
}
