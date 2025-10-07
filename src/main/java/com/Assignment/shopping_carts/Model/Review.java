package com.Assignment.shopping_carts.Model;

import com.Assignment.shopping_carts.Model.compositeKey.ReviewId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

/**
 * Review Entity Class
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-02 14:00
 */


@Data
@Entity
@IdClass(ReviewId.class)
public class Review {
    @Id
    private int productId;

    @Id
    private int customerId;

    private int rating;
    private String description;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;
}

