package com.Assignment.shopping_carts.Service;

/**
 * Orders Service Implementation Class
 * Author: Aung Kyaw Kyaw
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-14 10:00
 */

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Assignment.shopping_carts.InterfaceMethods.OrdersService;
import com.Assignment.shopping_carts.Model.Orders;
import com.Assignment.shopping_carts.Repository.OrdersRepository;

import jakarta.annotation.Resource;

@Service
@Transactional(readOnly = true)
public class OrdersServiceImpl implements  OrdersService{

  @Resource
  private OrdersRepository ordersRepository;

  @Override
  public List<Orders> getOrdersByCustomerId(Integer customerId) {
    return ordersRepository.findByCustomerIdWithDetails(customerId);
  }
}
