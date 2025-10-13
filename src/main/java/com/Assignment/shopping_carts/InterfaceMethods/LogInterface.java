package com.Assignment.shopping_carts.InterfaceMethods;

import com.Assignment.shopping_carts.Model.Customer;
import jakarta.servlet.http.HttpSession;


/**
 * Log interface
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Participants: Jason
 * Modified by: Jason
 * Last Modified: 2025-10-11 11:00
 */


public interface LogInterface {
    public Boolean LoginTry(String userName,String password);
    public Customer findByUserName(String username);
    public Boolean forgetPassword(String userName, String fullName,String email);
    public Customer updatePassword(String userName, String newPassword);
}
