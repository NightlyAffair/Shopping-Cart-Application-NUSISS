package com.Assignment.shopping_carts.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.Assignment.shopping_carts.InterfaceMethods.OrdersService;
import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Repository.OrdersRepository;

public class OrdersServiceImpl extends OrdersService{

  @Autowired
  private OrdersRepository ordersRepository;

  @Override
  public Customer getCustomer(int customerId) {
      return ordersRepository.findCustomerById(customerId);
  }
}
