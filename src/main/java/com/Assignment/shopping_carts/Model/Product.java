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
 * Modifier by : Glenn
 * Last Modified by : Glenn
 * Last Modified: 2025-10-04 04:00
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

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    private List<ShoppingCartDetail> shoppingCartDetails;

    @OneToMany(mappedBy = "product")
    private List<Favourites> favourites;
}
