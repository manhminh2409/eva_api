package com.evadeeva.evadeeva.web.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryRequest {
	private String title;
	
	private int status;
	
	private Date modifiedDate;

	private Date createdDate;
	 
	 private String description;

}
