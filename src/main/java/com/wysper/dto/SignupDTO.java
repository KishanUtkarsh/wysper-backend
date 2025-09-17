package com.wysper.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupDTO(
    @Email
    @NotBlank
    String email,
    @NotBlank
    String fname,
    @NotBlank
    String lname,
    @NotBlank
    String username,
    @NotBlank
    String enprikey,
    @NotBlank
    String enpubkey,
    @NotBlank
    String dtoken
) {
}
