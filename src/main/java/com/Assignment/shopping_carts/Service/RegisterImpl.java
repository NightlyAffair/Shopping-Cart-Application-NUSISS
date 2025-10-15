package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.DTO.CustomerRegisterDTO;
import com.Assignment.shopping_carts.InterfaceMethods.RegisterService;
import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * register service
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Participants: Jason
 * Modified by: Jason
 * Last Modified: 2025-10-11 11:00
 */

@Service
public class RegisterImpl implements RegisterService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean register(CustomerRegisterDTO dto) {
        if (customerRepository.existsByUserName(dto.getUserName())) {
            return false;
        }

        Customer customer = new Customer();
        customer.setUserName(dto.getUserName());
        customer.setPassword(dto.getPassword());
        customer.setFullName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());

        customerRepository.save(customer);
        return true;
    }

    @Override
    public boolean usernameExists(String userName) {
        return customerRepository.existsByUserName(userName);
    }
}