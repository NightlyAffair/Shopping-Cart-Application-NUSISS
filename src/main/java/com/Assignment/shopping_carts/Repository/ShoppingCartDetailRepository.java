package com.Assignment.shopping_carts.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Assignment.shopping_carts.Model.ShoppingCartDetail;
import com.Assignment.shopping_carts.Model.compositeKey.ShoppingCartDetailId;

/**
 * ShoppingCartDetail Repository
 * Author: Tony Song
 * Date: 2025-10-02
 * Modifier by : Tony Song
 * Last Modified by : Tony Song
 * Last Modified: 2025-10-07 23:00
 */
public interface ShoppingCartDetailRepository extends JpaRepository<ShoppingCartDetail, ShoppingCartDetailId>{
    //检查当前product是否在当前customer的cart中
    Optional<ShoppingCartDetail> findByCustomerIdAndProductId(int customerId, int productId);

    //查询某个customer所有的cart清单（因为是所有，customerId定位就够了）
    List<ShoppingCartDetail> findByCustomerId(int customerId);

    //删除当前customer cart中的某种product
    void deleteByCustomerIdAndProductId(int customerId, int productId);

    //删除当前customer cart中的所有product
    void deleteByCustomerId(int customerId);

}
