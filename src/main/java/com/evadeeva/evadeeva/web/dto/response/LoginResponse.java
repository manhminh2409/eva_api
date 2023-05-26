package com.evadeeva.evadeeva.web.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    private UserResponse user;
}
