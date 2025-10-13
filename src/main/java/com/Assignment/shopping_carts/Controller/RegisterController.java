package com.Assignment.shopping_carts.Controller;

import com.Assignment.shopping_carts.DTO.CustomerRegisterDTO;
import com.Assignment.shopping_carts.InterfaceMethods.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * register Controller
 * Author: Zhou Jason
 * Date: 2025-10-09
 * Participants: Jason
 * Modified by: Jason
 * Last Modified: 2025-10-13 14:00
 */


@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    @Autowired
    private RegisterService customerService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> register(
            @Valid @RequestBody CustomerRegisterDTO dto, BindingResult result) {

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("success", false);
            response.put("message", result.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(response);
        }

        boolean success = customerService.register(dto);

        if (success) {
            response.put("success", true);
            response.put("message", "Registration successful!");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Username already exists!");
            return ResponseEntity.status(409).body(response); // 409 Conflict means existed username
        }
    }

    @GetMapping("/check/{userName}")
    public ResponseEntity<Map<String, Object>> checkUserName(@PathVariable String userName) {
        Map<String, Object> response = new HashMap<>();
        boolean exists = customerService.usernameExists(userName);
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
}