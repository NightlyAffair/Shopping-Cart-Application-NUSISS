package com.Assignment.shopping_carts.Model;


import com.Assignment.shopping_carts.Model.compositeKey.FavouritesId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

/**
 * Favourites Entity Class
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-02 14:00
 */

@Entity
@Data
@IdClass(FavouritesId.class)
public class Favourites {
    /*
    - productId: int (PK/FK)
    - customerId: int (PK/FK)
     */
    @Id
    @Getter(AccessLevel.NONE)
    private int productId;

    @Id
    @Getter(AccessLevel.NONE)
    private int customerId;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;

}
