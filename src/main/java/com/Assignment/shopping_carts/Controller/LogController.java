package com.Assignment.shopping_carts.Controller;


import com.Assignment.shopping_carts.InterfaceMethods.LogInterface;
import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Repository.CustomerRepository;
import com.Assignment.shopping_carts.Service.LogImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Log Controller
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Participants: Jason
 * Modified by: Jason
 * Last Modified: 2025-10-07 11:00
 */


@RequestMapping("/Log")
@Controller
public class LogController {
    @Autowired
    private LogInterface logService;

    @Autowired
    public void setLogService(LogImpl logService) {
        this.logService = logService;
    }

    @GetMapping("")
    public String login(){
        return "login";
    }


    @PostMapping("/try")
    public String tryLogin(@RequestParam(name = "userName") String userName,@RequestParam(name = "password") String password,HttpSession session){
        if(logService.LoginTry(userName,password)){
            Customer customer = logService.findByUserName(userName);

            session.setAttribute("customerId",customer.getCustomerId());
            session.setAttribute("login_status",true);
            session.setAttribute("user_name",userName);
        }else {
            session.setAttribute("login_status",false);
        }
        return "redirect:/Log/homepage";
    }


    @GetMapping("/homepage")
    public String homepage(){
        return "homepage";
    }



}
