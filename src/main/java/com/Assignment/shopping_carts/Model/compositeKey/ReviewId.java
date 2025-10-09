package com.Assignment.shopping_carts.Model.compositeKey;


import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * ReviewId Composite Key
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Participants: Glenn, Jason
 * Modified by: Jason
 * Last Modified: 2025-10-07 11:00
 */

@EqualsAndHashCode
public class ReviewId implements Serializable {
    private int productId;
    private int customerId;
    private int orderId;
}
