package com.evadeeva.evadeeva.web.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class BannerRequest {
	private long id;

	private String name;

	private String image;

	private Date modifiedDate;

	private Date createdDate;
}
