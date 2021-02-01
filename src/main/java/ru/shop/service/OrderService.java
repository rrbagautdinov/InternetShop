package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.entity.Order;
import ru.shop.exception.OrderNotFoundException;
import ru.shop.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(Long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Заказ не найден!")
        );
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
