package com.Assignment.shopping_carts.Model;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Order Entity Class
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Modifier by : Aung Kyaw Kyaw
 * Last Modified by :
 * Last Modified: 2025-10-14 10:00
 */

@Entity
@Data
@Table(name = "orders")
public class Orders {
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

    private int customerId;
    private Date purchaseDate;
    private double unitAmount;
    private String status;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="customerId", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<Review> reviews;

    @Override
    public String toString() {
        return "Orders";
    }
}
