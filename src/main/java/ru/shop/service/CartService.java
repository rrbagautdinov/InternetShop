package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.shop.entity.Product;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartService {
    private HashMap<Optional<Product>, Integer> cartList;
    private final ProductService productService;

    public HashMap<Optional<Product>, Integer> showCart() {
        return cartList;
    }

    public void addProductToCart(Long id) {
        cartList.put(Optional.of(productService.findProductById(id).get()), 1);
    }

    public void deleteProductFromCart(Long id) {
        cartList.remove(Optional.of(productService.findProductById(id).get()));
    }

    @PostConstruct
    public void init() {
        cartList = new HashMap<>();
    }
}
