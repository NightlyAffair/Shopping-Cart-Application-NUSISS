package com.Assignment.shopping_carts.Model;


import com.Assignment.shopping_carts.Model.compositeKey.FavouritesId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(FavouritesId.class)
public class Favourites {
    /*
    - productId: int (PK/FK)
    - customerId: int (PK/FK)
     */
    @Id
    private int productId;
    @Id
    private int customerId;

}
