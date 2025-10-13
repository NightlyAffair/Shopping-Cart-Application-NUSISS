package com.Assignment.shopping_carts.Model;

import com.Assignment.shopping_carts.Model.compositeKey.ReviewId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Review Entity Class
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-02 14:00
 */


@Data
@Entity
@IdClass(ReviewId.class)
@Table(
    name = "review",
    uniqueConstraints = @UniqueConstraint(columnNames = {"productId", "customerId", "orderId"})
)
public class Review {
    @Id
    @Setter(AccessLevel.NONE)
    private int productId;

    @Id
    @Setter(AccessLevel.NONE)
    private int customerId;

    @Id
    @Setter(AccessLevel.NONE)
    private int orderId;

    private int rating;
    private String description;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", insertable = false, updatable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;
    
   
}
