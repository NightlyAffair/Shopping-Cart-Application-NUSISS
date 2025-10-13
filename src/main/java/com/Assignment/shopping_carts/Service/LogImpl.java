package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.LogInterface;
import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Log in and out service
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Participants: Jason
 * Modified by: Jason
 * Last Modified: 2025-10-11 11:00
 */


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
                System.out.println("login successful");
                return true;
            }

        }
        return false;
    }

    @Override
    @Transactional
    public Boolean forgetPassword(String userName, String fullName,String email){
        Optional<Customer> findByUserName = CusRep.findByUserName(userName);
        if(findByUserName.isPresent()){
            if(findByUserName.get().getFullName().equals(fullName)&&findByUserName.get().getEmail().equals(email)){
                System.out.println("information correct");
                return true;
            }
        }
        return false;

    }

    @Override
    @Transactional
    public Customer updatePassword(String userName, String newPassword){
        Customer target = CusRep.findByUserName(userName).get();
        target.setPassword(newPassword);
        CusRep.save(target);
        return target;
    }


    @PostConstruct
    public void initTestAccounts() {
        if (CusRep.count() == 0) {
            Customer c1 = new Customer();
            c1.setFullName("Jason Zhou");
            c1.setUserName("jason");
            c1.setEmail("jason@gmail.com");
            c1.setPassword("1234");
            c1.setAddress("Singapore");

            Customer c2 = new Customer();
            c2.setFullName("Glenn Tan");
            c2.setUserName("glenn");
            c2.setEmail("glenn@gmail.com");
            c2.setPassword("abcd");
            c2.setAddress("Tampines");

            Customer c3 = new Customer();
            c3.setFullName("Alice Lee");
            c3.setUserName("alice");
            c3.setEmail("alice@gmail.com");
            c3.setPassword("5678");
            c3.setAddress("Jurong");

            CusRep.save(c1);
            CusRep.save(c2);
            CusRep.save(c3);

            System.out.println("[LogImpl] Inserted 3 default customer accounts into database.");
        } else {
            System.out.println("[LogImpl] Customer table already has data, skip initialization.");
        }
    }



    @Override
    @Transactional
    public Customer findByUserName(String userName){
        return CusRep.findByUserName(userName).get();
    }

}
