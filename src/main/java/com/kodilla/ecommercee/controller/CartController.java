package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartDbService;
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

    private final CartMapper cartMapper;
    private final CartDbService cartDbService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartDbService.saveCart(cart);
    }

    @GetMapping("/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartDto getCart(@PathVariable Long cartId) throws CartNotFoundException {
        return cartMapper.mapToCartDto(cartDbService.getCart(cartId).orElseThrow(CartNotFoundException::new));
    }

    @PutMapping("/{cartId}?{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addProductToCart(@PathVariable Long cartId, @PathVariable Long productId){
        cartDbService.getCart(cartId);
        cartDbService.getCart(productId);
    }

    @DeleteMapping("/{cartId}?{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId){
        cartDbService.deleteById(cartId);
        cartDbService.deleteById(productId);
    }

    @PostMapping("/{cartId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody CartDto cartDto){
        cartDbService.saveCart(cartMapper.mapToCart(cartDto));
    }
}
