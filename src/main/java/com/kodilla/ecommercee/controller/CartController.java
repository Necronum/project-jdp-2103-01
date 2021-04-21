package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.OrderService;
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
    private final OrderMapper orderMapper;
    private final CartDbService cartDbService;
    private final OrderService orderService;
    private final CartRepository cartRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createCart(@RequestBody CartDto cartDto) {
        cartDbService.saveCart(cartMapper.mapToCart(cartDto));
    }

    @GetMapping("/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartDto getCart(@PathVariable Long cartId) throws CartNotFoundException {
        return cartMapper.mapToCartDto(cartDbService.getCart(cartId).orElseThrow(()
                -> new CartNotFoundException("Cart not found: " + cartId)));
    }

    @PutMapping("/{cartId}/{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addProductToCart(@PathVariable Long cartId, @PathVariable Long productId){
        cartDbService.getCart(cartId);
        cartDbService.getCart(productId);
    }

    @DeleteMapping("/{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable Long cartId) {
        cartDbService.deleteById(cartId);
    }

    @DeleteMapping("/{cartId}/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CartDto deleteProductFromCart(Long cartId, Product product){
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.getProducts().remove(product);
                    cartRepository.save(cart);
                    return cartMapper.mapToCartDto(cart);
                })
                .orElseThrow(() -> new CartNotFoundException("Cart not found!"));
    }

    @PostMapping("/{cartId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDto orderDto){
        orderService.saveOrder(orderMapper.mapToOrder(orderDto));
    }
}