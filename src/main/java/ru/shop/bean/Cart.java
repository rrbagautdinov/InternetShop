package ru.shop.bean;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.shop.entity.OrderItem;
import ru.shop.entity.Item;
import ru.shop.exception.ItemNotFoundException;
import ru.shop.service.ItemService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
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
                recalculateCart();
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

    public void incrementItem(Long id) {
        for (OrderItem oi : orderItems) {
            if (oi.getItem().getId().equals(id)) {
                oi.incrementProduct();
                recalculateCart();
                return;
            }
        }
    }

    public void decrementItem(Long id) {
        for (OrderItem oi : orderItems) {
            if (oi.getItem().getId().equals(id)) {
                if (oi.getQuantity() > 1) {
                    oi.decrementProduct();
                    recalculateCart();
                    return;
                }
                if (oi.getQuantity() == 1) {
                    orderItems.removeIf(op -> op.getItem().getId().equals(id));
                    recalculateCart();
                }
            }
        }
    }

    public void clearCart() {
        orderItems.clear();
        recalculateCart();
    }

    public void recalculateCart() {
        totalPrice = 0;
        for (OrderItem op : orderItems) {
            totalPrice += op.getTotalItemPrice();
        }
    }
}
