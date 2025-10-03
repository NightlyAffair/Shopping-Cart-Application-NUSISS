package com.Assignment.shopping_carts.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Product Entity Class
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
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
    
    @OneToMany(mappedBy="products")
    private List<ShoppingCartDetail> carts;
}
