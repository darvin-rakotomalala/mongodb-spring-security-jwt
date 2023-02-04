package com.poc.service.application;

import com.poc.mapper.UserResponseMapper;
import com.poc.model.dto.UserResponseDTO;
import com.poc.service.business.UserRSM;
import com.poc.utils.HelpPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserRSAImpl implements UserRSA {
    private final UserRSM userRSM;
    private final UserResponseMapper userMapper;

    @Override
    public UserResponseDTO getById(UUID id) {
        return userMapper.toDTO(userRSM.getById(id));
    }

    @Override
    public UserResponseDTO getByUsername(String username) {
        return userMapper.toDTO(userRSM.getByUsername(username));
    }

    @Override
    public HelpPage<UserResponseDTO> getAllUsers(Pageable pageable) {
        return userMapper.toDTO(userRSM.getAllUsers(pageable), pageable);
    }
}
