/**
 * Product Entity Class
 * Authors: Zhou Jason, Glenn Min, Sheng Qi
 * Date: 2025-10-02
 * Last Modified by: Glenn Min
 * New Updates: +imageUrl, averageRating, Annotations, Cascades
 * Last Modified: 2025-10-09
 */

package com.Assignment.shopping_carts.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }



    @Override
    public String toString() {
        return "1";
    }
}