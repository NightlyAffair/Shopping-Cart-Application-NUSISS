package com.Assignment.shopping_carts.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Assignment.shopping_carts.Model.Orders;
import com.Assignment.shopping_carts.Repository.OrderRepository;


@Controller
@RequestMapping("/purchaseHistory")
public class OrderController {

  @Autowired
  private OrderRepository orderRepo;

  @GetMapping("/{customerId}")
  public String getOrderById(@PathVariable int customerId, Model model) {
  List<Orders> orders = orderRepo.findByCustomerId(customerId);
  model.addAttribute("orders", orders);

  return "browse-purchase-history";
  }
}
