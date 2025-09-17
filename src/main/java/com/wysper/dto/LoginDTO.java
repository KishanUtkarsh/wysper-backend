package com.wysper.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    String username,
    String email,
    @NotBlank
    String dtoken

) {
}
