package com.Assignment.shopping_carts.model.compositeKey;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class OrderDetailId implements Serializable {
    private int orderId;
    private int productId;
}