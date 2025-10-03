package com.Assignment.shopping_carts.Model;

import com.Assignment.shopping_carts.Model.compositeKey.ReviewId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

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
    /*
    - productId: String (PK/FK)
    - customerId: String (PK/FK)
    - orderId: int (FK)
    - rating: int
    - description: String
     */

    @Id
    @Setter(AccessLevel.NONE)
    private int productId;
    @Id
    @Setter(AccessLevel.NONE)
    private int customerId ;
    private int orderId;
    private int rating;
    private String description;

}
