package com.Assignment.shopping_carts.Model.compositeKey;


import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ReviewId implements Serializable {
    private int productId;
    private int customerId;
}
