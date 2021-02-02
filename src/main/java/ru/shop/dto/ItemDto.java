package ru.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shop.entity.Item;


@Data
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private int price;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
    }
}
