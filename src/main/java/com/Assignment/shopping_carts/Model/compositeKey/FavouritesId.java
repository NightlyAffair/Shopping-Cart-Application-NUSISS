package com.Assignment.shopping_carts.Model.compositeKey;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class FavouritesId implements Serializable {
    private int productId;
    private int customerId;
}