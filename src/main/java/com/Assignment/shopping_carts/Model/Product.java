/**
 * Product Entity Class
 * Authors: Zhou Jason, Glenn Min, Sheng Qi
 * Date: 2025-10-02
 * Last Modified by: Glenn Min
 * New Updates: OrderDetails mapping fixed
 * Last Modified: 2025-10-15
 */

package com.Assignment.shopping_carts.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    private String productName;
    @Column(length = 500)
    private String description;
    private String imageUrl;
    @Min(0)
    @Max(1)
    private double discount;
    @Min(0)
    private double unitPrice;
    private Double averageRating;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetail> orderDetails = new ArrayList<>();


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ShoppingCartDetail> shoppingCartDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Favourites> favourites = new ArrayList<>();

    public Product() {
    }

    public Product(String productName, String description, String imageUrl,
                   Category category, double discount, double unitPrice) {
        this.productName = productName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.discount = discount;
        this.unitPrice = unitPrice;
        this.averageRating = 0.0;
    }


    @Override
    public String toString() {
        return "1";
    }
}