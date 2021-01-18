package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.shop.dto.ProductDto;
import ru.shop.entity.Product;
import org.springframework.stereotype.Service;
import ru.shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product productForUpdate = productRepository.findById(id).get();
        productForUpdate.setName(product.getName());
        productForUpdate.setPrice(product.getPrice());
        productRepository.save(productForUpdate);
        return productForUpdate;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
