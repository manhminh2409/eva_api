package com.evadeeva.evadeeva.web.dto.request;

import lombok.Data;

@Data
public class SizeRequest {
    private String value;

    private int total;

    private Long colorId;
}