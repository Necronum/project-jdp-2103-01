package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.ResourceNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductRepository productRepository;
    private final GroupRepository groupRepository;
    private final ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(@RequestParam Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct (Product product) {
        return productRepository.save(product);
    }

    public ProductDto updateProduct(@RequestParam Long id, ProductDto productDto) {
        Product product = getProduct(id).get();
        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        } if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        } if (productDto.getProductName() != null) {
            product.setName(productDto.getProductName());
        } if (productDto.getGroupId() != null) {
            product.setGroup(groupRepository.findById(productDto.getGroupId())
            .orElseThrow(() -> new ResourceNotFoundException("Group not found")));
        }
        Product updatedProduct = saveProduct(product);
        return productMapper.mapToProductDto(updatedProduct);
    }

    public void deleteProduct (Long id) {
        productRepository.deleteById(id);
    }
}
