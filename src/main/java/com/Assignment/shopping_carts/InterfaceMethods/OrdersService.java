package com.Assignment.shopping_carts.InterfaceMethods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Assignment.shopping_carts.Model.Orders;
import com.Assignment.shopping_carts.Repository.OrdersRepository;

public class OrdersService {

  @Autowired
  private OrdersRepository ordersRepository;

 public List<Orders> getOrdersByCustomerId(Integer customerId){
   return ordersRepository.findByCustomerId(customerId);
 }
}
