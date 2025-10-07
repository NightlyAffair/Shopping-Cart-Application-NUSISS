package com.Assignment.shopping_carts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Assignment.shopping_carts.Model.Customer;

public interface OrdersRepository extends JpaRepository<Customer, Integer>{
  Customer findCustomerById(int customerId);
}
