package com.Assignment.shopping_carts.InterfaceMethods;

import java.util.List;

import com.Assignment.shopping_carts.Model.ShoppingCartDetail;
/**
 * ShoppingCartDetail Service Interface
 * Author: Tony Song
 * Date: 2025-10-02
 * Modifier by : Tony Song
 * Last Modified by : Tony Song
 * Last Modified: 2025-10-07 23:00
 */

public interface ShoppingCartDetailInterface {
    //method：往购物车中添加商品
    void addProductToCart(int customerId, int productId, int quantity);

    //method：显示某个购物车的所有product
    List<ShoppingCartDetail> showCart(int customerId);

    //method：直接修改cart中某个product的数量
    void updateQuantity(int productId, int customerID, int newQuantity);

    //method：cart中的product数量+1
    void addOne(int customerId, int productId);

    //method：cart中的product数量-1
    void deleteOne(int customerId, int productId);

    //method：无视数量，删除cart中某种product
    void removeProduct(int customerId, int productId);

    //method：删除cart中所有product
    void clearCart(int customerId);

    //计算cart中的product总价
    double sumTotal(int customerId);

}
