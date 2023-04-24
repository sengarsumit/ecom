package com.ecommerce.ecweb.dto;

import com.ecommerce.ecweb.entity.Address;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterSellerDTO {
    @Pattern(regexp = "^(.+)@(\\S+)$",message = "Email is invalid")
    private String email;
    @Pattern(regexp ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "Password invalid")
    private String password;
    private String gst;
    private String companyName;
    private Address companyAddress;
    private long companyContact;
    private String confirmPassword;
    private String firstName;
    private String lastName;
}
