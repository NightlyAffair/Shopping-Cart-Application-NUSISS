package com.Assignment.shopping_carts.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

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
- categoryId: int (FK)
- discount: Double
- unitPrice: Double
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int productId;
    private String productName;
    private String Description;
    private int categoryId;
    private double discount;
    private double unitPrice;

    @OneToMany(mappedBy = "product")
    private List<Favourites> favourites;
    @OneToMany(mappedBy = "product")
    private List<ShoppingCartDetail> cart;

}
