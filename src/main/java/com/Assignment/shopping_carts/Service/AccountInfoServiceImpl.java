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

        if (customer.getUserName() == null || customer.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (customer.getUserName().length() < 4) {
            throw new IllegalArgumentException("Username must be at least 4 characters");
        }
        if (customer.getUserName().length() > 20) {
            throw new IllegalArgumentException("Username must be 4–20 characters");
        }


        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!customer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }


        if (customer.getFullName() == null || customer.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is required");
        }


        if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (customer.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        if (customer.getPassword().length() > 1000) {
            throw new IllegalArgumentException("Password must be less than 1000 characters");
        }

        accountInfoRepository.save(customer);
    }

    public void deleteCustomer(String id) {
        accountInfoRepository.delete(getCustomer(id));
    }
}
