package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shop.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
