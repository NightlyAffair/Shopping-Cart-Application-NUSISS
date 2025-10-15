package com.Assignment.shopping_carts.Repository;

/**
 * Orders Repository Class
 * Author: Aung Kyaw Kyaw
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-14 10:00
 */

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Assignment.shopping_carts.Model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{
  List<Orders> findByCustomerId(@Param("customerId") Integer customerId);

//  @EntityGraph(attributePaths = {"orderDetails", "orderDetails.product"})
//  @Query("SELECT o FROM Orders o WHERE o.customerId = :customerId ORDER BY o.orderId DESC")
//  List<Orders> findByCustomerIdWithDetails(@Param("customerId") int customerId);
}
