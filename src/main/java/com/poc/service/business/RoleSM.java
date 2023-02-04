package com.poc.service.business;

import com.poc.model.constant.ERole;
import com.poc.model.domain.Role;

import java.util.Optional;

public interface RoleSM {
    Optional<Role> getByName(ERole name);
}
