package com.ecommerce.ecweb.controller;

import com.ecommerce.ecweb.dto.AuthResponseDTO;
import com.ecommerce.ecweb.dto.LoginDto;
import com.ecommerce.ecweb.dto.RegisterDto;
import com.ecommerce.ecweb.entity.Role;
import com.ecommerce.ecweb.entity.User;
import com.ecommerce.ecweb.repository.RoleRepository;
import com.ecommerce.ecweb.repository.UserRepository;
import com.ecommerce.ecweb.security.JWTgenerator;
import com.ecommerce.ecweb.service.CustomUserDeatilService;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTgenerator jwTgenerator;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,JWTgenerator jwTgenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwTgenerator=jwTgenerator;
    }
    @Autowired
    private CustomUserDeatilService customUserDeatilService;
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto)
    {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUseremail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwTgenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
    }
//    @PostMapping("register")
//    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto)
//    {
//        if(userRepository.existsByUserEmail(registerDto.getUseremail()))
//        {
//            return new ResponseEntity<>("Email is already registered", HttpStatus.BAD_REQUEST);
//
//        }
//        User user = new User();
//        user.setUserEmail(registerDto.getUseremail());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//        Role roles =roleRepository.findByAuthority("USER").get();
//        user.setRoles(Collections.singletonList(roles));
//        userRepository.save(user);
//        return new ResponseEntity<>("user registered succes",HttpStatus.OK);
//    }

}
