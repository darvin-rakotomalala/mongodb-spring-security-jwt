package com.poc.service.business;

import com.poc.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserRSM {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User getById(UUID id);
    User getByUsername(String username);
    Page<User> getAllUsers(Pageable pageable);
}
