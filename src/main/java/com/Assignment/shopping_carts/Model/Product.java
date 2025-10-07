package com.Assignment.shopping_carts.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Product Entity Class
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Modified by: Glenn
 * Last Modified: 2025-10-04 04:00
 */

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int productId;

    private String productName;
    private String description;
    private double discount;
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ShoppingCartDetail> shoppingCartDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Favourites> favourites = new ArrayList<>();

    public Product() {
    }

    public Product(String productName, String description, Category category,
                   double discount, double unitPrice) {
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.discount = discount;
        this.unitPrice = unitPrice;
    }
}