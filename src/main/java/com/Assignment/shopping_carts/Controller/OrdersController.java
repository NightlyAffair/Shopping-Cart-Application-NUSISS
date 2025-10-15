package com.Assignment.shopping_carts.Controller;

/**
 * Orders Controller Class
 * Author: Aung Kyaw Kyaw
 * Date: 2025-10-02
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-14 10:00
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Model.Orders;
import com.Assignment.shopping_carts.Service.OrdersServiceImpl;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/api/purchaseHistory")
public class OrdersController {

  @Autowired
  OrdersServiceImpl ordersService;


  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<Orders>> getCustomerById(@PathVariable("customerId") int customerId, HttpSession session) {
    try{
      List<Orders> orders = ordersService.getOrdersByCustomerId(customerId);
      if(!orders.isEmpty()){
        return new ResponseEntity<>(orders, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }


}