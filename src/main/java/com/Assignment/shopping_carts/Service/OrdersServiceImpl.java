package com.Assignment.shopping_carts.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Assignment.shopping_carts.InterfaceMethods.OrdersService;
import com.Assignment.shopping_carts.Model.Orders;
import com.Assignment.shopping_carts.Repository.OrdersRepository;

@Service
public class OrdersServiceImpl extends OrdersService{

  @Autowired
  private OrdersRepository ordersRepository;

  @Override
  public List<Orders> getOrdersByCustomerId(Integer customerId) {
    return ordersRepository.findByCustomerId(customerId);
  }
}
