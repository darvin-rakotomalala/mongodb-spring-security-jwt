package com.poc.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String username;
    @Email
    @Indexed(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private Set<String> roles;
    private Instant createdAt;
    private Instant updatedAt;
}
