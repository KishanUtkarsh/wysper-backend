package com.wysper.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse <T>{
    private String message;
    private boolean success;
    private T data;
    private String timestamp;

    private ApiResponse(){
        this.timestamp = LocalDateTime.now().toString();
    }

    public static <T> ApiResponse<T> success(String message, T dto){
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.message = message;
        response.data = dto;
        return response;
    }

    public  static <T> ApiResponse<T> success(T dto){

        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.message = "Request successful";
        response.data = dto;
        return response;

    }

    public static <T> ApiResponse<T> error(String message){
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.message = message;
        response.data = null;
        return response;
    }
}
