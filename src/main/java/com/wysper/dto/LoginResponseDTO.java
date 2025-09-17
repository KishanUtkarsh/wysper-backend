package com.wysper.dto;

import java.util.UUID;

public record LoginResponseDTO (

    UUID uuid,
    String username,
    String email,
    String jwtToken,
    String fname,
    String lname,
    String smessage,
    String ppicid,
    String phone

){
}
