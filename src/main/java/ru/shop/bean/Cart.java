package ru.shop.bean;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shop.entity.OrderItem;
import ru.shop.entity.Item;
import ru.shop.exception.ItemNotFoundException;
import ru.shop.service.ItemService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class Cart {
    private final ItemService itemService;
    private List<OrderItem> orderItems;
    private int totalPrice;

    @PostConstruct
    public void initCart() {
        orderItems = new ArrayList<>();
    }

    public void addToCart(Long id) throws ItemNotFoundException {
        for (OrderItem op : orderItems) {
            if (op.getItem().getId().equals(id)) {
                op.incrementProduct();
                return;
            }
        }
        Item item = itemService.findItemById(id);
        OrderItem orderItem = new OrderItem(item);
        orderItems.add(orderItem);
        recalculateCart();
    }

    public void deleteFromCart(Long id) {
        orderItems.removeIf(op -> op.getItem().getId().equals(id));
        recalculateCart();
    }

    public void clearCart() {
        orderItems.clear();
        recalculateCart();
    }

    public void recalculateCart() {
        totalPrice = 0;
        for (OrderItem op : orderItems) {
            totalPrice += op.getPrice();
        }
    }
}
