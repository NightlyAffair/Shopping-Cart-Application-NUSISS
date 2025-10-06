package com.Assignment.shopping_carts.Controller;


import com.Assignment.shopping_carts.InterfaceMethods.LogInterface;
import com.Assignment.shopping_carts.Repository.CustomerRepository;
import com.Assignment.shopping_carts.Service.LogImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    public String tryLogin(@RequestParam(name = "useName") String userName,@RequestParam(name = "password") String password,HttpSession session){
        if(logService.LoginTry(userName,password)){
            session.setAttribute("login_status",true);
        }else {
            session.setAttribute("login_status",false);
        }
        return "redirect:/homepage";
    }

}
