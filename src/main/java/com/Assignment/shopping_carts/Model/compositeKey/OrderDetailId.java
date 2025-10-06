package com.Assignment.shopping_carts.Model.compositeKey;

import com.Assignment.shopping_carts.Model.Order;
import com.Assignment.shopping_carts.Model.Product;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * OrderDetailId Composite Key
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Modified by: Glenn
 * Last Modified: 2025-10-06 12:00
 */


@EqualsAndHashCode
public class OrderDetailId implements Serializable {
    private Order order;
    private Product product;
}