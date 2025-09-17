package com.wysper.common.utils.mapper;

import com.wysper.model.User;
import com.wysper.dto.*;

import java.util.Date;
import java.util.UUID;

public class UserMapper {

    /**
     * Converts SignupDTO to User entity
     */
    public static User toEntity(SignupDTO dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setFname(dto.fname());
        user.setLname(dto.lname());
        user.setUsername(dto.username());
        user.setEnprivkey(dto.enprikey());
        user.setEnpubkey(dto.enpubkey());
        user.setDtoken(dto.dtoken());
        user.setSmessage("Available"); // default status message
        user.setPpicid("defaultppic.png"); // default profile picture
        user.setIsactive(false); // default status
        user.setEverified(false);
        user.setDeactivated(false);
        user.setDeleted(false);
        user.setCreatedat(new Date());
        user.setLastseen(new Date());
        return user;
    }

    /**
     * Converts User entity to LoginResponseDTO
     */
    public static LoginResponseDTO toLoginResponseDTO(User user, String jwtToken) {
        return new LoginResponseDTO(
                user.getUuid(),
                user.getUsername(),
                user.getEmail(),
                jwtToken,
                user.getFname(),
                user.getLname(),
                user.getSmessage(),
                user.getPpicid(),
                user.getPhone()
        );
    }

    /**
     * Extracts login credentials from LoginDTO
     */
    public static String getLoginIdentifier(LoginDTO dto) {
        return dto.email() != null ? "email" : "username";
    }

    /**
     * Verifies OTP request mapping
     */
    public static String getOtpIdentifier(OtpValidationDTO dto) {
        return dto.email() != null ? "email" : "username";
    }
}
