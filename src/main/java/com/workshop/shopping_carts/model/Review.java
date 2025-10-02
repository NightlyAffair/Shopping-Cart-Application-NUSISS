package com.workshop.shopping_carts.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;


@Data
@Entity
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
