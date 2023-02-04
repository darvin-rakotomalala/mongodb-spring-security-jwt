package com.poc.service.application;

import com.poc.model.dto.LoginRequestDTO;
import org.springframework.http.ResponseEntity;

public interface UserAuthSA {
    ResponseEntity<?> authenticateUser(LoginRequestDTO loginRequest);
}
