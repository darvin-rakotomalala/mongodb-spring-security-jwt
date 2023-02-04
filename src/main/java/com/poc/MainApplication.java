package com.poc;

import com.poc.model.constant.ERole;
import com.poc.model.domain.Role;
import com.poc.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class MainApplication implements ApplicationRunner {

    private final RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("############################   RUN   ############################");

        roleRepository.deleteAll();
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(UUID.randomUUID(), ERole.ROLE_ADMIN));
        roles.add(new Role(UUID.randomUUID(), ERole.ROLE_MODERATOR));
        roles.add(new Role(UUID.randomUUID(), ERole.ROLE_USER));
        roles.add(new Role(UUID.randomUUID(), ERole.ROLE_VISITOR));
        for (Role role : roles) {
            roleRepository.save(role);
        }
    }

}
