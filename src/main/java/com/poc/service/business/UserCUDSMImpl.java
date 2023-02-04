package com.poc.service.business;

import com.poc.exception.ErrorsEnum;
import com.poc.exception.FunctionalException;
import com.poc.model.domain.User;
import com.poc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserCUDSMImpl implements UserCUDSM {
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        try {
            log.info("----- deleteUser : {}", id);
            User userFound = userRepository.findById(id)
                    .orElseThrow(() -> new FunctionalException(ErrorsEnum.ERR_MCS_USER_NOT_FOUND.getErrorMessage()));
            userRepository.deleteById(userFound.getId());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

}
