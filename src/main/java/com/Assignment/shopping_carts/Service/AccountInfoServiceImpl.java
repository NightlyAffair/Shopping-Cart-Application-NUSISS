package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.AccountInfoService;
import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Repository.AccountInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AccountInfoServiceImpl
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-06
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-06 14:00
 */

@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    public Customer getCustomer(String id) {
        Integer intId = Integer.parseInt(id);
        Customer customer = accountInfoRepository.getCustomerByCustomerId(intId);
        System.out.println(customer);
        return customer;
    }

    public void saveCustomer(Customer customer) {
        accountInfoRepository.save(customer);
    }

    public void deleteCustomer(String id) {
        accountInfoRepository.delete(getCustomer(id));
    }
}
