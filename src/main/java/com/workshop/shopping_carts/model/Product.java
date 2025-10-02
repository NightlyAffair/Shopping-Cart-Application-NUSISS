package com.workshop.shopping_carts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Customer Entity Class
 *
 * Description:
 * - Maps to the "customer" table in the database.
 * - The primary key (ID) is auto-generated and cannot be manually modified.
 *
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Last Modified: 2025-10-02 14:00
 */

@Data
@Entity
public class Product {
/*
- id: int (PK)
- productName: String
- description: String
- category: String
- discount: Double
- unitPrice: Double
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int productId;
    private String productName;
    private String Description;
    private String category;
    private double discount;
    private double unitPrice;
}
