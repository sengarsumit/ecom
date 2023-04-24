package com.ecommerce.ecweb.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterCustomerDTO {
    @Pattern(regexp = "^(.+)@(\\S+)$",message = "Email is invalid")
    private String email;
    private long phoneNO;
    @Pattern(regexp ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "Password invalid")
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;

}
