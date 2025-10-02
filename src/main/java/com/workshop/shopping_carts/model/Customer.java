package com.workshop.shopping_carts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;


/**
 * Customer Entity Class
 *
 * Description:
 * - Maps to the "customer" table in the database.
 * - The primary key (ID) is auto-generated and cannot be manually modified.
 *
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Last Modified: 2025-10-02 14:00
 */



@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String address;




}
