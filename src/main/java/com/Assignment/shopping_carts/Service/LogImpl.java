package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.LogInterface;
import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class LogImpl implements LogInterface {

    @Autowired
    private CustomerRepository CusRep;


    @Override
    @Transactional
    public Boolean LoginTry(String userName,String password){
        Optional<Customer> findByUserName = CusRep.findByUserName(userName);
        if(findByUserName.isPresent()){
            if(findByUserName.get().getPassword().equals(password)){
                return true;
            }

        }
        return false;
    }

}
