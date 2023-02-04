package com.poc.repository;

import com.poc.model.constant.ERole;
import com.poc.model.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends MongoRepository<Role, UUID> {
    Optional<Role> findByName(ERole name);
}
