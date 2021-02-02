package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.shop.entity.User;
import ru.shop.exception.UserNotFoundException;
import ru.shop.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Page<User> findAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page - 1, size));

    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) throws UserNotFoundException {
        User userForUpdate = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Пользователь не найден!")
        );
        userForUpdate.setLogin(user.getName());
        userForUpdate.setPassword(user.getPassword());
        userForUpdate.setName(user.getName());
        userForUpdate.setSurname(user.getSurname());
        userForUpdate.setEmail(user.getEmail());
        userRepository.save(userForUpdate);
        return userForUpdate;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
