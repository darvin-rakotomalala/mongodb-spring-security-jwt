package com.poc.service.business;

import com.poc.model.domain.User;

import java.util.UUID;

public interface UserCUDSM {
    User saveUser(User user);
    void deleteUserById(UUID id);
}
