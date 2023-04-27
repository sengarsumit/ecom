package com.ecommerce.ecweb.controller;

import com.ecommerce.ecweb.dto.RegisterCustomerDTO;
import com.ecommerce.ecweb.dto.RegisterSellerDTO;
import com.ecommerce.ecweb.repository.UserRepository;
import com.ecommerce.ecweb.service.SellerServiceimpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerServiceimpl sellerServiceimpl;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterSellerDTO registerSellerDto) throws MethodArgumentNotValidException {
        return sellerServiceimpl.registerSeller(registerSellerDto);
    }
    @PutMapping("/activate")
    public ResponseEntity<?> activate(@RequestParam(required = false) String token) {
        return sellerServiceimpl.activateSeller(token);
    }
}
