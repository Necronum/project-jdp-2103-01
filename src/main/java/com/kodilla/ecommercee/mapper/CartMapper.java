package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartMapper {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Cart mapToCart(final CartDto cartDto){
        return new Cart(
                cartDto.getId(),
                userRepository.findById(cartDto.getUserId()).get(),
                cartDto.getProductsId().stream()
                        .map(productRepository::findById)
                        .map(Optional::get)
                        .collect(Collectors.toList()));
    }

    public CartDto mapToCartDto(final Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cart.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()));
    }

    public List<CartDto> mapToCartDtoList(final List <Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }

    public List<Cart> mapToCartList(final List<CartDto> cartDtoList) {
        return cartDtoList.stream()
                .map(this::mapToCart)
                .collect(Collectors.toList());
    }
}
