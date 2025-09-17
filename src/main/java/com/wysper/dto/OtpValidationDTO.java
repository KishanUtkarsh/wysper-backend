package com.wysper.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record OtpValidationDTO(
    @Email
    String email,
    String username,
    @NotBlank
    String otpCode
) { }