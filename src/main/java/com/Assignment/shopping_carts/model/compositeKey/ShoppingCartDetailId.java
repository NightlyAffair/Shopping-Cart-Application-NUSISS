package com.Assignment.shopping_carts.model.compositeKey;


import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ShoppingCartDetailId implements Serializable {
    private int productId;
    private int customerID;
}
