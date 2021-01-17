package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.entity.Product;
import ru.shop.exception.ProductNotFoundException;

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

//    public void addProductToCart(Long id) throws ProductNotFoundException {
//        if (productService.findProductById(id).isPresent()) {
//            Optional<Product> product = Optional.of(productService.findProductById(id).get());
//            if (cartList.containsKey(product)) {
//                cartList.put(product, cartList.get(product) + 1);
//            } else {
//                cartList.put(product, 1);
//            }
//        } else {
//            throw new ProductNotFoundException("Продукт с id: " + id + " не найден!");
//        }
//    }

    public void addProductToCart(Long id) throws ProductNotFoundException {
        Optional<Product> product = productService.findProductById(id);
        if (product.isPresent()) {
            if (!cartList.containsKey(product)) {
                cartList.put(product, 1);
            }
            if (cartList.containsKey(product)) {
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

    @PostConstruct
    public void init() {
        cartList = new HashMap<>();
    }
}
