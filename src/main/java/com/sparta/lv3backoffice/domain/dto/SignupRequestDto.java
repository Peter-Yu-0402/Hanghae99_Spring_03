package com.sparta.lv3backoffice.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    //@NotBlank
    private String username;
    //@NotBlank
    private String password;
    private String department;
    //@Email
    //@NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}