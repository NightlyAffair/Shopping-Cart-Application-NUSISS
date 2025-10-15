package com.Assignment.shopping_carts.Service;

/**
 * Orders Service Implementation Class
 * Author: Aung Kyaw Kyaw
 * Date: 2025-10-02
 * Modifier by : Nithvin(Refund)
 * Last Modified by :
 * Last Modified: 2025-10-14 10:00
 */

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Assignment.shopping_carts.Model.OrderDetail;
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

  @Override
  @Transactional
  public boolean refund(Integer order_id, Integer product_id) {
    try{
      Orders order = ordersRepository.getOrdersByOrderId(order_id);

      if(order == null) {
        System.out.println("Order not found: " + order_id);
        return false;
      }

      System.out.println(order);

      List<OrderDetail> orderDetails = order.getOrderDetails();
      boolean productFound = false;

      for(OrderDetail orderDetail : orderDetails) {
        if(orderDetail.getProductId() == product_id) {
          orderDetail.setIsRefunded(true);
          productFound = true;
          break;
        }
      }

      if(!productFound) {
        System.out.println("Product not found in order: " + product_id);
        return false;
      }

      ordersRepository.save(order);

      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

  }
}
