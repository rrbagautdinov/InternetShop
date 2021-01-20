package ru.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shop.entity.Product;
import ru.shop.exception.ProductNotFoundException;
import ru.shop.service.CartService;

import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Map<Product, Integer> showCart() {
        return cartService.showCart();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id) {
        try {
            cartService.addProductToCart(id);
        } catch (ProductNotFoundException e) {
            log.error("Ошибка добавления продукта в корзину. Продукт c ID: " + id + " не найден в магазине");
        }
    }

    @GetMapping("/delete/{id}")
    public void deleteProductFromCart(@PathVariable Long id) {
        try {
            cartService.deleteProductFromCart(id);
        } catch (ProductNotFoundException e) {
            log.error("Ошибка удаления продукта из корзины. Продукт c ID: " + id + " не найден в корзине");
        }
    }

    @GetMapping("/delete/all")
    public void deleteAllProductFromCart() {
        cartService.deleteAllProductFromCart();
    }
}
