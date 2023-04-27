package com.ecommerce.ecweb.service;

import com.ecommerce.ecweb.dto.AuthResponseDTO;
import com.ecommerce.ecweb.dto.LoginDto;
import com.ecommerce.ecweb.dto.MessageResponseDTO;
import com.ecommerce.ecweb.entity.User;
import com.ecommerce.ecweb.repository.RoleRepository;
import com.ecommerce.ecweb.repository.UserRepository;
import com.ecommerce.ecweb.security.JWTgenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
@Service
public class UserServiceimpl implements UserService {
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


    @Override
    public ResponseEntity<MessageResponseDTO> loginUser(LoginDto loginDto) throws MethodArgumentNotValidException {

        if(userRepository.existsByUserEmail(loginDto.getUseremail())) {
            User user = userRepository.findByUserEmail(loginDto.getUseremail()).get();

            Authentication authentication;
            try {
                authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUseremail(), loginDto.getPassword()));
            }
            catch (BadCredentialsException e) {
                user.setFAttempts(user.getFAttempts() + 1);
                if (user.getFAttempts() == 3) {
                    user.setLocked(true);
                    return new ResponseEntity<>(new MessageResponseDTO("user is locked due to 3 unsucsesfull attempts"), HttpStatus.LOCKED);
                }
                userRepository.save(user);
                return new ResponseEntity<>(new MessageResponseDTO("Wrong password entered"),HttpStatus.NOT_FOUND);
            }
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUseremail(), loginDto.getPassword()));
                if (user.isActive()) {
                    if (!user.isLocked()) {
                        if (authentication.isAuthenticated()) {
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            String token = jwTgenerator.generateToken(authentication);
                            user.setFAttempts(0);
                            return new ResponseEntity<>(new MessageResponseDTO(token), HttpStatus.OK);

                        }
                    }
                    else {
                        return new ResponseEntity<>(new MessageResponseDTO("user is locked due to 3 unsucsesfull attempts"), HttpStatus.LOCKED);

                    }

                } else {
                    return new ResponseEntity<>(new MessageResponseDTO("user is inactive plz activate first"), HttpStatus.FORBIDDEN);
                }


        }
            return new ResponseEntity<>(new MessageResponseDTO("User not registered"),HttpStatus.NOT_FOUND);

    }
}
