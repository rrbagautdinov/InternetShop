package ru.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.shop.entity.Product;
import ru.shop.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<Product> findAllProducts(@RequestParam Optional<String> name,
                                         @RequestParam Optional<Integer> page,
                                         @RequestParam Optional<Integer> size) {
        return productService.findProductByName(name.orElse("_"),
                PageRequest.of(page.orElse(0), size.orElse(10)));
    }

    @PostMapping("/add")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/edit/{id}")
    public Product updateProduce(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @GetMapping("/get/{id}")
    public Optional<Product> findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
