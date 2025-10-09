package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.DTO.CustomerRegisterDTO;
import com.Assignment.shopping_carts.InterfaceMethods.RegisterService;
import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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