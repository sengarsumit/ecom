package com.ecommerce.ecweb.controller;

import com.ecommerce.ecweb.dto.RegisterCustomerDTO;
import com.ecommerce.ecweb.dto.RegisterSellerDTO;
import com.ecommerce.ecweb.repository.UserRepository;
import com.ecommerce.ecweb.service.SellerServiceimpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerServiceimpl sellerServiceimpl;
    @Autowired
    private UserRepository userRepository;
}