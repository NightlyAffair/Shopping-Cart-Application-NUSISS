package com.Assignment.shopping_carts.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.sql.Date;

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
}
