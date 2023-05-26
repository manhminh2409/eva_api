package com.evadeeva.evadeeva.web.dto.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private String password;

    private String cfPassword;
}
