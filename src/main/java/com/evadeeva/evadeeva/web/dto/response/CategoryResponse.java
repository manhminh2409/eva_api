package com.evadeeva.evadeeva.web.dto.response;

import com.evadeeva.evadeeva.models.dtos.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
	private List<CategoryDto> content;
	
	private int pageNo;
	
	private int pageSize;
	
	private long totalElements;
	
	private int totalPages;
	
	private boolean Last;
	
	/*
	 * private String title;
	 * 
	 * private int status;
	 * 
	 * private Date modifiedDate;
	 * 
	 * private Date createdDate;
	 * 
	 * private String description;
	 */
}
