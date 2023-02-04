package com.poc.controller;

import com.poc.repository.CustomMongoRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "request")
public class CustomRequestController {

    @Autowired
    private CustomMongoRepository customMongoRepository;

    @Operation(summary = "WS used to execute request")
    @PostMapping("")
    public Object executeRequest(@RequestBody String request) {
        return customMongoRepository.executMongoRequest(request);
    }

}
