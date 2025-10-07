package com.Assignment.shopping_carts.Model.compositeKey;

import java.io.Serializable;

import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Model.Product;
import lombok.EqualsAndHashCode;

/**
 * FavouritesId Composite Key
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Participants: Glenn, Jason
 * Modified by: Jason
 * Last Modified: 2025-10-07 11:00
 */



@EqualsAndHashCode
public class FavouritesId implements Serializable {
    private int productId;
    private int customerId;
}