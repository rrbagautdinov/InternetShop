package ru.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @Column(name = "total_item_price")
    private int totalItemPrice;

    public OrderItem(Item item) {
        this.item = item;
        this.quantity = 1;
        this.price = item.getPrice();
        this.totalItemPrice = item.getPrice() * quantity;
    }

    public void incrementProduct() {
        quantity++;
        totalItemPrice = item.getPrice() * quantity;
    }

    public void decrementProduct() {
        quantity--;
        totalItemPrice = item.getPrice() * quantity;;
    }

}
