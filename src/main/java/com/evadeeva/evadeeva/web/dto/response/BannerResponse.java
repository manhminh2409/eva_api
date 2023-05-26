package com.evadeeva.evadeeva.web.dto.response;

import com.evadeeva.evadeeva.models.dtos.BannerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerResponse {
	
	private List<BannerDto> content;
	
	private int pageNo;
	
	private int pageSize;
	
	private long totalElements;
	
	private int totalPages;
	
	private boolean Last;
	
	/*
	 * private String name;
	 * 
	 * private String imgage;
	 * 
	 * private int status;
	 * 
	 * private Date modifiedDate;
	 * 
	 * private Date createdDate;
	 * 
	 */
}
