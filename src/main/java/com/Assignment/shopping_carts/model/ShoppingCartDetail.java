package com.Assignment.shopping_carts.model;


import com.Assignment.shopping_carts.model.compositeKey.ShoppingCartDetailId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
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
    @Setter(AccessLevel.NONE)
    private int productId;
    @Id
    @Setter(AccessLevel.NONE)
    private int customerID;
    private int quantity;

    @ManyToOne
    private Customer customers;

}
