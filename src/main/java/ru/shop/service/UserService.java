package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.entity.Role;
import ru.shop.entity.User;
import ru.shop.exception.UserNotFoundException;
import ru.shop.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
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

    public Optional<User> findByLogin(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
