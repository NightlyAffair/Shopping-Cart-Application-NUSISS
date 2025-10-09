package com.Assignment.shopping_carts.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Assignment.shopping_carts.Model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{
  List<Orders> findByCustomerId(Integer customerId);
}
