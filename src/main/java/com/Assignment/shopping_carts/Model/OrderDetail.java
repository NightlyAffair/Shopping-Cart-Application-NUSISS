package com.Assignment.shopping_carts.Model;


import com.Assignment.shopping_carts.Model.compositeKey.OrderDetailId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * OrderDetail Entity Class
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-02 14:00
 */

@Entity
@Data
@IdClass(OrderDetailId.class)
public class OrderDetail {
    /*
    - orderId: int (PK,FK)
    - productId: int (FK,PK)
    - quantity: int
    - isRefunded: Boolean
     */
    @Id
    @Setter(AccessLevel.NONE)
    private int orderId;
    @Id
    @Setter(AccessLevel.NONE)
    private int productId;
    private int quantity;
    private boolean isRefunded;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

}
