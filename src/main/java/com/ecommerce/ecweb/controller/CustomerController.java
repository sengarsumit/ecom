package com.ecommerce.ecweb.controller;

import com.ecommerce.ecweb.dto.LoginDto;
import com.ecommerce.ecweb.dto.RegisterCustomerDTO;
import com.ecommerce.ecweb.entity.User;
import com.ecommerce.ecweb.repository.UserRepository;
import com.ecommerce.ecweb.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterCustomerDTO registerCustomerDto) throws MethodArgumentNotValidException {
        return customerService.registerCustomer(registerCustomerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return customerService.loginCustomer(loginDto);
    }

    @PutMapping("/activate")
    public ResponseEntity<?> activate(@RequestParam(required = false) String token) {
        return customerService.activateCustomer(token);
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
