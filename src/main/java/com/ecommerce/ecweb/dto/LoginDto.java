package com.ecommerce.ecweb.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDto {
    @Pattern(regexp = "^(.+)@(\\S+)$",message = "Email is invalid")
    private String useremail;
    @Pattern(regexp ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "Password invalid")
    private String password;
}
