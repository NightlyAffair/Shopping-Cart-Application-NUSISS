package com.Assignment.shopping_carts.Repository;

import com.Assignment.shopping_carts.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Customer Repository
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Participants: Jason
 * Modified by: Jason
 * Last Modified: 2025-10-07 11:00
 */

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    boolean existsByUserName(String userName);
    Optional<Customer> findByUserName(String userName);
    public Customer findById(int id);
}
