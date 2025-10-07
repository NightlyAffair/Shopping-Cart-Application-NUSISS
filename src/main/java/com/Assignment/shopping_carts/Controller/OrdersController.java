package com.Assignment.shopping_carts.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Service.OrdersServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/purchaseHistory")
public class OrdersController {

  @Autowired
  OrdersServiceImpl ordersService;

  @GetMapping("/{customerId}")
  public ResponseEntity<Customer> getCustomer(@RequestParam String customerId) {
      try {
          return new ResponseEntity<>(ordersService.getCustomer(customerId), HttpStatus.OK);
      } catch (Exception e) {
      }
  }
  
}
