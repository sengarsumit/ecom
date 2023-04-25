package com.ecommerce.ecweb.service;

import com.ecommerce.ecweb.dto.AuthResponseDTO;
import com.ecommerce.ecweb.dto.LoginDto;
import com.ecommerce.ecweb.dto.MessageResponseDTO;
import com.ecommerce.ecweb.dto.RegisterCustomerDTO;
import com.ecommerce.ecweb.entity.Customer;
import com.ecommerce.ecweb.entity.Role;
import com.ecommerce.ecweb.entity.User;
import com.ecommerce.ecweb.repository.CustomerRepository;
import com.ecommerce.ecweb.repository.RoleRepository;
import com.ecommerce.ecweb.repository.UserRepository;
import com.ecommerce.ecweb.security.JWTgenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
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
    public ResponseEntity<MessageResponseDTO> registerCustomer(RegisterCustomerDTO registerCustomerDTO) throws MethodArgumentNotValidException {
        Customer customer=new Customer();
        System.out.println(registerCustomerDTO);
        System.out.println(customer);
        customer.setFirstName(registerCustomerDTO.getFirstName());
        customer.setLname(registerCustomerDTO.getLastName());
        customer.setUserEmail(registerCustomerDTO.getEmail());
        customer.setContact(registerCustomerDTO.getPhoneNO());
        System.out.println(customer);
        if(!registerCustomerDTO.getPassword().equals(registerCustomerDTO.getConfirmPassword()))
        {
            return ResponseEntity.badRequest().body(new MessageResponseDTO("password and confirm password doesn't match"));
        }
        customer.setPassword(passwordEncoder.encode(registerCustomerDTO.getPassword()));
        Role role=roleRepository.findByAuthority("CUSTOMER").get();
        customer.setRoles(List.of(role));
        customerRepository.save(customer);
        String token= jwTgenerator.generateToken(registerCustomerDTO.getEmail());
        //email servicce
        emailServices.sendMail(registerCustomerDTO.getEmail(),token);
        return new ResponseEntity<>(new MessageResponseDTO("Customer registered"), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<AuthResponseDTO> loginCustomer(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUseremail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwTgenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
    }
    public ResponseEntity<?> activateCustomer(String userToken)
    {
        boolean isValid=jwTgenerator.validateToken(userToken);
        String email=jwTgenerator.getUseremailfromJWT(userToken);
        System.out.println(isValid);
        System.out.println(email);
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
