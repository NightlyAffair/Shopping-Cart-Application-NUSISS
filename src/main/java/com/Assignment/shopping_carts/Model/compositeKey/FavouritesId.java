package com.Assignment.shopping_carts.Model.compositeKey;

import java.io.Serializable;

import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Model.Product;
import lombok.EqualsAndHashCode;

/**
 * FavouriteId Composite Key
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Modified by: Glenn
 * Last Modified: 2025-10-06 12:00
 */


@EqualsAndHashCode
public class FavouritesId implements Serializable {
    private Product product;
    private Customer customer;
}