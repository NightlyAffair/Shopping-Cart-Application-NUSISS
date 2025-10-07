package com.Assignment.shopping_carts.Repository;

import com.Assignment.shopping_carts.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AccountInfoRepo
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-06
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-06 14:00
 */

public interface AccountInfoRepository extends JpaRepository<Customer, Integer> {
    Customer getCustomerByCustomerId(Integer intId);
}
