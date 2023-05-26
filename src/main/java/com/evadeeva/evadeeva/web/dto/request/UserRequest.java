package com.evadeeva.evadeeva.web.dto.request;


import lombok.Data;

@Data
public class UserRequest {

    private String email;

    private String fullname;

    private String phone;

    private String image;
}
