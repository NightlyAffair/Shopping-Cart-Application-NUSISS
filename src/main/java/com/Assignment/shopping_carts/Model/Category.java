package com.Assignment.shopping_carts.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Category Entity Class
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Modifier by : yh
 * Last Modified by : yh
 * Last Modified: 2025-10-04 15:00
 */
//Tony's comment!!!!!!
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


    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {}

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

}
