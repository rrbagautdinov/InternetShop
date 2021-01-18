package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.shop.entity.Product;
import ru.shop.exception.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartService {
    private final Map<Optional<Product>, Integer> cartList = new HashMap<>();
    private final ProductService productService;

    public Map<Optional<Product>, Integer> showCart() {
        return cartList;
    }

    public void addProductToCart(Long id) throws ProductNotFoundException {
        Optional<Product> product = productService.findProductById(id);
        if (product.isPresent()) {
            if (!cartList.containsKey(product)) {
                cartList.put(product, 1);
            } else {
                cartList.put(product, cartList.get(product) + 1);
            }
        } else {
            throw new ProductNotFoundException("Продукт с id: " + id + " не найден!");
        }
    }


    public void deleteProductFromCart(Long id) throws ProductNotFoundException {
        Optional<Product> product = productService.findProductById(id);
        if (cartList.containsKey(product)) {
            cartList.remove(product);
        } else {
            throw new ProductNotFoundException("Продукт с id: " + id + " не найден в корзине!");
        }
    }

    public void deleteAllProductFromCart() {
        cartList.clear();
    }
}
