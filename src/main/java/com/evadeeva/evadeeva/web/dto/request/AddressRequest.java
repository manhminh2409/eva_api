package com.evadeeva.evadeeva.web.dto.request;

import lombok.Data;

@Data
public class AddressRequest {
    private String firstName;

    private String lastName;

    private String phone;

    private String company;

    private String addressDetail;

    private String religion;

    private long userId;
}
