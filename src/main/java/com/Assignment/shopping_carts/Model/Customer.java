package com.Assignment.shopping_carts.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import java.util.List;



/**
 * Customer Entity Class
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-02 14:00
 */


@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int customerId;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<ShoppingCartDetail> cart;

    @OneToMany(mappedBy = "customer")
    private List<Favourites> favourites;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;


}
