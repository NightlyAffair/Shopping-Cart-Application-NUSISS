package com.Assignment.shopping_carts.InterfaceMethods;

import com.Assignment.shopping_carts.Model.Customer;

public interface AccountInfoService {
    public Customer getCustomer(String id);

    public void saveCustomer(Customer customer);

    public void deleteCustomer(String id);
}
