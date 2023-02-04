package com.poc.mapper;

import com.poc.common.mapper.DtoMapper;
import com.poc.model.domain.User;
import com.poc.model.dto.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserResponseMapper extends DtoMapper<UserResponseDTO, User> {

}
