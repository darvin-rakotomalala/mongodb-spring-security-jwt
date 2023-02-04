package com.poc.controller;

import com.poc.constraint.validation.LoginValidator;
import com.poc.constraint.validation.UserValidator;
import com.poc.model.dto.LoginRequestDTO;
import com.poc.model.dto.UserRequestDTO;
import com.poc.model.dto.UserResponseDTO;
import com.poc.service.application.UserAuthSA;
import com.poc.service.application.UserCUDSA;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "users/auth")
public class UserAuthController {

    private final UserAuthSA userAuthSA;
    private final UserCUDSA userCUDSA;
    private final UserValidator userValidator;
    private final LoginValidator loginValidator;

    @InitBinder("userDTO")
    protected void initUserDTOBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @InitBinder("loginRequestDTO")
    protected void initLoginRequestDTOBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }

    @Operation(summary = "WS used to signIn")
    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody @Validated LoginRequestDTO loginRequest) {
        return userAuthSA.authenticateUser(loginRequest);
    }

    @Operation(summary = "WS used to signup")
    @PostMapping("/signup")
    public UserResponseDTO registerUser(@RequestBody @Validated UserRequestDTO userDto) {
        return userCUDSA.registerUser(userDto);
    }

}
