package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
