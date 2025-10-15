package com.Assignment.shopping_carts.InterfaceMethods;

/**
 * Orders Service Interface
 * Author: Aung Kyaw Kyaw
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-14 10:00
 */

import java.util.List;

import com.Assignment.shopping_carts.Model.Orders;

public interface  OrdersService {
  public List<Orders> getOrdersByCustomerId(Integer customerId);
}
