package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.entity.Product;
import ru.shop.exception.ProductNotFoundException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Map<Product, Integer> cartList;

    public Map<Product, Integer> showCart() {
        return cartList;
    }

    @PostConstruct
    public void init() {
        cartList = new HashMap<>();
    }

    public void addProductToCart(Long id) throws ProductNotFoundException {
        Product product = productService.findProductById(id).orElseThrow(()-> new ProductNotFoundException("Продукт не найден в магазине"));
        if (!cartList.containsKey(product)) {
            cartList.put(product, 1);
        } else {
            cartList.put(product, cartList.get(product) + 1);
        }
    }

    public void deleteProductFromCart(Long id) throws ProductNotFoundException {
        Product product = productService.findProductById(id).orElseThrow(()-> new ProductNotFoundException("Продукт не найден в магазине"));
        if (cartList.containsKey(product)) {
            cartList.remove(product);
        } else {
            throw new ProductNotFoundException("Продукт не найден в корзине");
        }

    }

    public void deleteAllProductFromCart() {
        cartList.clear();
    }
}
