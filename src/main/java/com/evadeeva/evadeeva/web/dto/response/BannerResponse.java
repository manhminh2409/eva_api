package com.evadeeva.evadeeva.web.dto.response;

import lombok.Data;

@Data
public class BannerResponse {
	private long id;
	private String name;
	private String image;
	private long categoryId;
}
