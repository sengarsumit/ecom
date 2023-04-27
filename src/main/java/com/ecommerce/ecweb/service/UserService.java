package com.ecommerce.ecweb.service;

import com.ecommerce.ecweb.dto.LoginDto;
import com.ecommerce.ecweb.dto.MessageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface UserService {
    public ResponseEntity<MessageResponseDTO> loginUser(LoginDto loginDto)throws MethodArgumentNotValidException;
    }

