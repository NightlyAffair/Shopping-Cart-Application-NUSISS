package com.Assignment.shopping_carts.Controller;

/**
 * Orders Controller Class
 * Author: Aung Kyaw Kyaw
 * Date: 2025-10-02
 * Modifier by : Nithvin(Refund)
 * Last Modified by :
 * Last Modified: 2025-10-14 10:00
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


  @GetMapping("/customer")
  public ResponseEntity<List<Orders>> getCustomerById(HttpSession session) {
    int customerId = (int)session.getAttribute("customerId");
    try {
      List<Orders> orders = ordersService.getOrdersByCustomerId(customerId);
      if (!orders.isEmpty()) {
        return new ResponseEntity<>(orders, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //Add refund
  @PostMapping("/refund/{order_id}/{product_id}")
  public ResponseEntity<String> refund(@PathVariable("order_id") int order_id, @PathVariable("product_id") int product_id){
    boolean response = ordersService.refund(order_id, product_id);

    if(response){
      return new ResponseEntity<>("Refunded", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}