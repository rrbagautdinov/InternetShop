package ru.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shop.bean.Cart;
import ru.shop.dto.CartDto;
import ru.shop.exception.ItemNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final Cart cart;

    @GetMapping
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addItemToCart(@PathVariable Long id) throws ItemNotFoundException {
        cart.addToCart(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteItemToCart(@PathVariable Long id) {
        cart.deleteFromCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clearCart();
    }

    @GetMapping("/{id}/increment")
    public void incrementItem(@PathVariable Long id) {
        cart.incrementItem(id);
    }

    @GetMapping("/{id}/decrement")
    public void decrementItem(@PathVariable Long id) {
        cart.decrementItem(id);
    }
}
