package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
