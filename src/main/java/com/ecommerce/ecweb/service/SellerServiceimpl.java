package com.ecommerce.ecweb.service;

import com.ecommerce.ecweb.dto.MessageResponseDTO;
import com.ecommerce.ecweb.dto.RegisterSellerDTO;
import com.ecommerce.ecweb.entity.Role;
import com.ecommerce.ecweb.entity.Seller;
import com.ecommerce.ecweb.entity.User;
import com.ecommerce.ecweb.repository.CustomerRepository;
import com.ecommerce.ecweb.repository.RoleRepository;
import com.ecommerce.ecweb.repository.SellerRepository;
import com.ecommerce.ecweb.repository.UserRepository;
import com.ecommerce.ecweb.security.JWTgenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.UUID;

@Service
public class SellerServiceimpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTgenerator jwTgenerator;
    @Autowired
    EmailServices emailServices;
    @Override
    public ResponseEntity<MessageResponseDTO> registerSeller(RegisterSellerDTO registerSellerDTO) throws MethodArgumentNotValidException {
        Seller seller=new Seller();
        System.out.println(registerSellerDTO);
        System.out.println(seller);
        seller.setUserEmail(registerSellerDTO.getEmail());
        seller.setGST(registerSellerDTO.getGst());
        seller.setComp_name(registerSellerDTO.getCompanyName());
        seller.setComp_no(registerSellerDTO.getCompanyContact());
        seller.setFirstName(registerSellerDTO.getFirstName());
        seller.setLname(registerSellerDTO.getLastName());
        seller.setAddress(registerSellerDTO.getCompanyAddress());
        Role role=roleRepository.findByAuthority("SELLER").get();
        seller.setRoles(List.of(role));
        System.out.println(seller);
        if(!registerSellerDTO.getPassword().equals(registerSellerDTO.getConfirmPassword()))
        {
            return ResponseEntity.badRequest().body(new MessageResponseDTO("password and confirm password doesn't match"));
        }
        seller.setPassword(passwordEncoder.encode(registerSellerDTO.getConfirmPassword()));
        sellerRepository.save(seller);
        String token=jwTgenerator.generateToken(registerSellerDTO.getEmail());
        emailServices.sendMail(registerSellerDTO.getEmail(), token);
        return new ResponseEntity<>(new MessageResponseDTO("Seller registered"), HttpStatus.OK);


    }
    public ResponseEntity<?> activateSeller(String userToken)
    {
        boolean isValid=jwTgenerator.validateToken(userToken);
        String email=jwTgenerator.getUseremailfromJWT(userToken);
        if(!isValid)
        {
            return new ResponseEntity<>(new MessageResponseDTO("Token is wrong or exxpired"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user=userRepository.findByUserEmail(email).get();
        //set active
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponseDTO("Account is active"));
    }
}
