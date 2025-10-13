package com.Assignment.shopping_carts.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO to set validation
 * Author: Zhou Jayson
 * Date: 2025-10-06
 * Participants:
 * Modified by: Jayson
 * Last Modified: 2025-10-07 13:00
 */


@Data
public class CustomerRegisterDTO {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 4, max = 20, message = "Username must be 4–20 characters")
    private String userName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 1000, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    private String address;
}
