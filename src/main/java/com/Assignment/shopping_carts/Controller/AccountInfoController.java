package com.Assignment.shopping_carts.Controller;

import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Service.AccountInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account-info")
public class AccountInfoController {

    @Autowired
    AccountInfoServiceImpl accountInfoService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
        try {
            return new ResponseEntity<>(accountInfoService.getCustomer(id), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/save")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        try {
            accountInfoService.saveCustomer(customer);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        try {
            accountInfoService.deleteCustomer(id);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
