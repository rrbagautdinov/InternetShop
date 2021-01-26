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

    @Column(name = "total_order_price")
    private int totalOrderPrice;

    public OrderItem(Item item) {
        this.item = item;
        this.quantity = 1;
        this.totalOrderPrice = item.getPrice() * this.quantity;
    }

    public void incrementProduct() {
        quantity++;
        totalOrderPrice = item.getPrice() * quantity;
    }

    public void decrementProduct() {
        quantity--;
        totalOrderPrice = item.getPrice() * quantity;
    }

}
