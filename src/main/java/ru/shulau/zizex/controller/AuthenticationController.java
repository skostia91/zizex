package ru.shulau.zizex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shulau.zizex.auth.AuthenticationRequest;
import ru.shulau.zizex.auth.AuthenticationResponse;
import ru.shulau.zizex.auth.AuthenticationService;
import ru.shulau.zizex.auth.RegisterRequest;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        log.info("register {}", request.getName());
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        log.info("authenticate {}", request.getUsername());
        return ResponseEntity.ok(service.authenticate(request));
    }
}