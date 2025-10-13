package com.Assignment.shopping_carts.InterfaceMethods;

import java.util.List;

import com.Assignment.shopping_carts.Model.Orders;

public interface  OrdersService {
  public List<Orders> getOrdersByCustomerId(Integer customerId);
}
