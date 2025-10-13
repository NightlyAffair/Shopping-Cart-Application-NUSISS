package com.Assignment.shopping_carts.Model;


import com.Assignment.shopping_carts.Model.compositeKey.ShoppingCartDetailId;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;


/**
 * ShoppingCartDetail Entity Class
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Modifier by : Tony
 * Last Modified by : Tony
 * Last Modified: 2025-10-07 23:00
 */


@Entity
@Data
@IdClass(ShoppingCartDetailId.class)
public class ShoppingCartDetail {
    /*
    - productId: int(PK,FK)
    - customerID: int (PK,FK)
    - quantity: int
     */
    @Id
    private int productId;
    @Id
    private int customerId;
    private int quantity;

    @ManyToOne
    //分别写明：当前表的FK（customerId），对应customer表的PK（customerId），插入数据时不要重复插入，更新数据时不要重复更新数据
    //insertable=False意思是插入数据库时，只用customerId字段的值，不要再从customerEntity里取了
    //updatable=false意思是更新数据库时，也只用customerId字段的值，不要再从customerEntity里取了
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    //分别写明：当前表的FK（productId），对应customer表的PK（productId），插入数据时不要重复插入，更新数据时不要重复更新数据
    //insertable=False意思是插入数据库时，只用customerId字段的值，不要再从customerEntity里取了
    //updatable=false意思是更新数据库时，也只用customerId字段的值，不要再从customerEntity里取了
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;

    //一个method，计算单种product的总价（单价*数量），method同时考虑有discount的情况
    public double getSubPrice() {
        //先get单价，再考虑有discount的情况，计算某个product的最终单价
        double priceAfterDiscount = product.getUnitPrice() * (1 - product.getDiscount());
        return priceAfterDiscount * quantity;//乘数量获得单种product的总价
    }
}
