package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shop.entity.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
