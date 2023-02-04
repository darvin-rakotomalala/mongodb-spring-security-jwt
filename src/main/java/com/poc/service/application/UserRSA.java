package com.poc.service.application;

import com.poc.model.dto.UserResponseDTO;
import com.poc.utils.HelpPage;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserRSA {
    UserResponseDTO getById(UUID id);
    UserResponseDTO getByUsername(String username);
    HelpPage<UserResponseDTO> getAllUsers(Pageable pageable);
}
