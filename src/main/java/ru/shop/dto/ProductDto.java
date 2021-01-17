package ru.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shop.entity.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private int price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
