package com.Assignment.shopping_carts.Model;


import com.Assignment.shopping_carts.Model.compositeKey.ShoppingCartDetailId;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;


/**
 * ShoppingCartDetail Entity Class
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-02 14:00
 */


@Entity
@Data
@IdClass(ShoppingCartDetailId.class)
public class ShoppingCartDetail {
    /*
    - productId: int(PK,FK)
    - customerID: int (PK,FK)
    - quantity: int
     */
    @Id
    private int productId;
    @Id
    private int customerId;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;

    public double getSubPrice() {
        //Also considered discount
        double priceAfterDiscount = product.getUnitPrice() * (1 - product.getDiscount());
        return priceAfterDiscount * quantity;
    }
}
