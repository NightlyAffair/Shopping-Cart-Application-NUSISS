package com.Assignment.shopping_carts.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Favourites Entity Class
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-02 14:00
 */

@Entity
@Data
public class Favourites {
    /*
    - productId: int (PK/FK)
    - customerId: int (PK/FK)
     */
    @Id
    @Setter(AccessLevel.NONE)
    private int productId;
    @Id
    @Setter(AccessLevel.NONE)
    private int customerId;

}
