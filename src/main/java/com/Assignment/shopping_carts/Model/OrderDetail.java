package com.Assignment.shopping_carts.Model;


import com.Assignment.shopping_carts.Model.compositeKey.OrderDetailId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * OrderDetail Entity Class
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Modifier by : Aung Kyaw Kyaw
 * Last Modified by :
 * Last Modified: 2025-10-14 10:00
 */

@Entity
@Data
@IdClass(OrderDetailId.class)
public class OrderDetail {

    @Id
    @Setter(AccessLevel.NONE)
    private int orderId;
    @Id
    @Setter(AccessLevel.NONE)
    private int productId;
    private int quantity;
    private boolean isRefunded;

    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    @JsonIgnore
    private Orders orders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    public OrderDetail() {

    }
    public OrderDetail(int orderId, int productId, int quantity, boolean isRefunded) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.isRefunded = isRefunded;
    }

    @Override
    public String toString() {
        return "OrderDetail";
    }

    public void setIsRefunded(Boolean status) {
        this.isRefunded = status;
    }
}
