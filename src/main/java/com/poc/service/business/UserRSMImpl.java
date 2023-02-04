package com.poc.service.business;

import com.poc.exception.ErrorsEnum;
import com.poc.exception.FunctionalException;
import com.poc.model.domain.User;
import com.poc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserRSMImpl implements UserRSM {
    private final UserRepository userRepository;

    @Override
    public Boolean existsByUsername(String username) {
        try {
            log.info("----- existsByUsername : {}", username);
            return userRepository.existsByUsername(username);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Boolean existsByEmail(String email) {
        try {
            log.info("----- existsByEmail : {}", email);
            return userRepository.existsByEmail(email);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User getById(UUID id) {
        try {
            log.info("----- getById : {}", id);
            return userRepository.findById(id)
                    .orElseThrow(() -> new FunctionalException(ErrorsEnum.ERR_MCS_USER_NOT_FOUND.getErrorMessage()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User getByUsername(String username) {
        try {
            log.info("----- getByUsername : {}", username);
            Optional<User> userFound = userRepository.findByUsername(username);
            if (userFound.isEmpty()) {
                throw new FunctionalException(ErrorsEnum.ERR_MCS_USER_USERNAME_NOT_FOUND.getErrorMessage());
            }
            return userFound.get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        try {
            log.info("----- getAllUsers");
            return userRepository.findAll(pageable);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
