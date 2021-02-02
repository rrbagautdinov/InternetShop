package ru.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shop.entity.OrderItem;


@NoArgsConstructor
@Data
public class OrderItemDto {
    private Long id;
    private String productTitle;
    private int quantity;
    private int price;
    private int productsPrice;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getItem().getId();
        this.productTitle = orderItem.getItem().getName();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
        this.productsPrice = orderItem.getQuantity() * orderItem.getPrice();
    }
}
