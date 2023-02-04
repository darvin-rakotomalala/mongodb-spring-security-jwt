package com.poc.service.application;

import com.poc.exception.ErrorsEnum;
import com.poc.exception.FunctionalException;
import com.poc.constraint.validation.EmailValidator;
import com.poc.mapper.UserResponseMapper;
import com.poc.model.constant.ERole;
import com.poc.model.domain.Role;
import com.poc.model.dto.UserRequestDTO;
import com.poc.mapper.UserMapper;
import com.poc.model.dto.UserResponseDTO;
import com.poc.service.business.RoleSM;
import com.poc.service.business.UserCUDSM;
import com.poc.service.business.UserRSM;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserCUDSAImpl implements UserCUDSA {
    private final UserCUDSM userCUDSM;
    private final UserRSM userSM;
    private final RoleSM roleSM;
    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;
    private final PasswordEncoder encoder;
    private final EmailValidator emailValidator;

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userDto) {

        if (Boolean.TRUE.equals(userSM.existsByUsername(userDto.getUsername()))) {
            throw new FunctionalException(ErrorsEnum.ERR_MCS_USER_USERNAME_EXIST.getErrorMessage());
        }

        boolean isValidEmail = emailValidator.
                isValid(userDto.getEmail());
        if (!isValidEmail) {
            throw new FunctionalException(ErrorsEnum.ERR_MCS_EMAIL_INVALID.getErrorMessage());
        }

        if (Boolean.TRUE.equals(userSM.existsByEmail(userDto.getEmail()))) {
            throw new FunctionalException(ErrorsEnum.ERR_MCS_USER_EMAIL_EXIST.getErrorMessage());
        }

        // Create new user's account
        userDto.setId(UUID.randomUUID());
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        Set<String> strRoles = userDto.getRoles();
        Set<String> roles = new HashSet<>();
        if (strRoles.isEmpty()) {
            roles.add("ROLE_USER");
        } else {
            strRoles.forEach(role -> {
                Optional<Role> otherRole = roleSM.getByName(ERole.valueOf(role));
                roles.add(String.valueOf(otherRole.get().getName()));
            });
        }
        userDto.setRoles(roles);

        return userResponseMapper.toDTO(userCUDSM.saveUser(userMapper.toDO(userDto)));
    }

    @Override
    public void deleteUser(UUID id) {
        userCUDSM.deleteUserById(id);
    }

}
