package ru.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shop.bean.Cart;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@Data
public class CartDto {
    private List<OrderItemDto> items;
    private int totalPrice;

    public CartDto(Cart cart) {
        this.totalPrice = cart.getTotalPrice();
        this.items = cart.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
