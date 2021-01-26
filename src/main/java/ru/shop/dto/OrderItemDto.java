package ru.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shop.entity.OrderItem;


@NoArgsConstructor
@Data
public class OrderItemDto {
    private Long id;
    private String name;
    private int quantity;
    private int price;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getItem().getId();
        this.name = orderItem.getItem().getName();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
    }
}
