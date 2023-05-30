package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.SocialMedia;
import com.evadeeva.evadeeva.models.dtos.SocialMediaDto;
import org.mapstruct.Mapper;

@Mapper
public interface SocialMediaMapper {
    SocialMediaDto mapToDto(SocialMedia socialMedia);
    SocialMedia mapToModel(SocialMediaDto socialMediaDto);

}
