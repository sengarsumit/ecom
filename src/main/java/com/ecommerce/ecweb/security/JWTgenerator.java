package com.ecommerce.ecweb.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTgenerator {
    public String generateToken(Authentication authentication)
    {
        String username=authentication.getName();
        Date currentdate= new Date();
        Date expiryDate=new Date(currentdate.getTime()+SecurityConstants.JWT_EXPIRATION);
        String token= Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.ES512,SecurityConstants.JWT_SECRET)
                .compact();
        return token;
    }

}
