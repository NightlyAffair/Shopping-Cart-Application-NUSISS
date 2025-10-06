package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.AccountInfoService;
import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Repository.AccountInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    public Customer getCustomer(String id) {
        Integer intId = Integer.parseInt(id);
        return accountInfoRepository.getCustomerByCustomerId(intId);
    }

    public void saveCustomer(Customer customer) {
        accountInfoRepository.save(customer);
    }

    public void deleteCustomer(String id) {
        accountInfoRepository.delete(getCustomer(id));
    }
}
