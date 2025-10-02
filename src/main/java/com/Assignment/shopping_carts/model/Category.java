package com.Assignment.shopping_carts.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;


/**
 * Category Entity Class
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-02 14:00
 */

@Data
@Entity
public class Category {
    /*
    - id: int (PK)
    - name: String
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int categoryId;
    private String name;



}
