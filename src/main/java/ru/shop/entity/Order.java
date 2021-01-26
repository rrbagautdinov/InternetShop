//package ru.shop.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "orders", schema = "shop")
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @ManyToMany
//    @JoinTable(name = "orders_products",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    @Column(name = "user_id")
//    private List<Product> productList = new ArrayList<>();
//
//    @Column(name = "total_price")
//    private int totalPrice;
//
//    @Column(name = "payed")
//    private boolean isPayed;
//}
