package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Category;
import com.evadeeva.evadeeva.models.dtos.CategoryDto;
import com.evadeeva.evadeeva.web.dto.request.CategoryRequest;
import com.evadeeva.evadeeva.web.dto.response.CategoryResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
@Mapper
public interface CategoryMapper {
	//Map model to response
	CategoryResponse mapModelToResponse(Category category);

		// mapper one model to dto
		CategoryDto mapModelToDTO(Category category);

		// mapper list model to dto
		List<CategoryDto> mapModelToDTOs(List<Category> categories);

		// mapper one dto to model
		Category mapDTOToModel(CategoryDto categoryDto);

		// mapper list dto to model
		List<Category> mapDTOToModels(List<CategoryDto> categoryDtos);

		@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
		void updateModel(@MappingTarget Category category, CategoryRequest categoryRequest);

}
