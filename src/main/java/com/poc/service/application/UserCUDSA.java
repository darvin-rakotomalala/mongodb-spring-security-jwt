package com.poc.service.application;

import com.poc.model.dto.UserRequestDTO;
import com.poc.model.dto.UserResponseDTO;

import java.util.UUID;

public interface UserCUDSA {
    UserResponseDTO registerUser(UserRequestDTO userDto);
    void deleteUser(UUID id);
}
