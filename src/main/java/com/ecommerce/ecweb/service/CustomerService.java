package com.ecommerce.ecweb.service;

import com.ecommerce.ecweb.dto.AuthResponseDTO;
import com.ecommerce.ecweb.dto.LoginDto;
import com.ecommerce.ecweb.dto.MessageResponseDTO;
import com.ecommerce.ecweb.dto.RegisterCustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface CustomerService {
    public ResponseEntity<MessageResponseDTO> registerCustomer(RegisterCustomerDTO registerCustomerDTO)throws MethodArgumentNotValidException;
    public ResponseEntity<AuthResponseDTO> loginCustomer(LoginDto loginDto);
}
