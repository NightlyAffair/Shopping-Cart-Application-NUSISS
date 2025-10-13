package com.Assignment.shopping_carts.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRegisterDTO {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 4, max = 20, message = "Username must be 4–20 characters")
    private String userName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 18, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    private String address;
}
