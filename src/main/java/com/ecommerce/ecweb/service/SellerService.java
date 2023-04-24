package com.ecommerce.ecweb.service;

import com.ecommerce.ecweb.dto.MessageResponseDTO;
import com.ecommerce.ecweb.dto.RegisterCustomerDTO;
import com.ecommerce.ecweb.dto.RegisterSellerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface SellerService {
    public ResponseEntity<MessageResponseDTO> registerSeller(RegisterSellerDTO registerSellerDTO)throws MethodArgumentNotValidException;
}
