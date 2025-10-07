package com.Assignment.shopping_carts.Repository;

import com.Assignment.shopping_carts.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<Customer, Integer> {
    Customer getCustomerByCustomerId(Integer intId);
}
