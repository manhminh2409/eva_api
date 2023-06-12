package com.evadeeva.evadeeva.web.dto.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class AddressResponse {
    private long id;

    private String firstName;

    private String lastName;

    private String phone;

    private String company;

    private String addressDetail;

    private String religion;

    private Date createdDate;

    private Date modifiedDate;

    private int focus;

    private int status;
}
