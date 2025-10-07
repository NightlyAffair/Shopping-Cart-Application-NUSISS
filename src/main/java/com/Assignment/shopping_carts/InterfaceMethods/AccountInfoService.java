package com.Assignment.shopping_carts.InterfaceMethods;

import com.Assignment.shopping_carts.Model.Customer;

/**
 * AccountInfoService
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-06
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-06 14:00
 */

public interface AccountInfoService {
    public Customer getCustomer(String id);

    public void saveCustomer(Customer customer);

    public void deleteCustomer(String id);
}
