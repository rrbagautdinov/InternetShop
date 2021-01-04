package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.shop.model.Product;
import org.springframework.stereotype.Service;
import ru.shop.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findByName(String name, Pageable pageable) {
        return productRepository.findByName(name, pageable);
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
        return productRepository.save(productForUpdate);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}
