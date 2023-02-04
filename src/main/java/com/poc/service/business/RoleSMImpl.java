package com.poc.service.business;

import com.poc.exception.ErrorsEnum;
import com.poc.exception.FunctionalException;
import com.poc.model.constant.ERole;
import com.poc.model.domain.Role;
import com.poc.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleSMImpl implements RoleSM {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> getByName(ERole name) {
        try {
            log.info("----- findByName : {}", name);
            Optional<Role> roleFound = roleRepository.findByName(name);
            if (roleFound.isEmpty()) {
                throw new FunctionalException(ErrorsEnum.ERR_MCS_ROLE_NOT_FOUND.getErrorMessage());
            }
            return roleFound;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

}
