package com.Assignment.shopping_carts.InterfaceMethods;

import com.Assignment.shopping_carts.DTO.CustomerRegisterDTO;

public interface RegisterService {

    // @return true = success, false = exited account
    boolean register(CustomerRegisterDTO dto);
    boolean usernameExists(String userName);
}