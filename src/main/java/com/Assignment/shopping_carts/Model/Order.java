package com.Assignment.shopping_carts.Model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * Order Entity Class
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-02 14:00
 */

@Entity
@Data
@Table(name = "orders")
public class Order {
    /*
    - id: int (PK)
    - customerId: int (FK)
    - purchaseDate: LocalDateTime
    - unitAmount: Double
    - status: String
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int orderId;

    private String customerId;
    private Date purchaseDate;
    private double unitAmount;
    private String status;
    @OneToMany(mappedBy="order")
    private List<OrderDetail> orderDetails;
    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<Review> reviews;
}
