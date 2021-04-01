package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {
    public List<ProductDto> createEmptyCart(){
        return new ArrayList<>();
    }

    public List<ProductDto> getCat(){
        return new ArrayList<>();
    }

    public void addProductToCart(ProductDto productDto){
        //do nothing
    }

    public void deleteProductFromCart(Long productId){
        //do nothing
    }

    public void createOrder(List<ProductDto> cart){
        //do nothing
    }
}
