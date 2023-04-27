package com.ecommerce.ecweb.controller;

import com.ecommerce.ecweb.dto.LoginDto;
import com.ecommerce.ecweb.dto.MessageResponseDTO;
import com.ecommerce.ecweb.repository.RoleRepository;
import com.ecommerce.ecweb.repository.UserRepository;
import com.ecommerce.ecweb.security.JWTgenerator;
import com.ecommerce.ecweb.service.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTgenerator jwTgenerator;
    @Autowired
    private UserServiceimpl userServiceimpl;
    @PostMapping("login")
    public ResponseEntity<MessageResponseDTO> login(@RequestBody LoginDto loginDto) throws MethodArgumentNotValidException {
       return userServiceimpl.loginUser(loginDto);
    }

}
