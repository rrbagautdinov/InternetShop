package ru.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shop.entity.User;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String name;
    private String surname;
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
    }
}
