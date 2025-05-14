package org.example.springtask1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springtask1.security.dto.LoginRequestDto;
import org.example.springtask1.security.dto.LoginResponse;
import org.example.springtask1.security.dto.RefreshResponse;
import org.example.springtask1.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authenticationService.authenticate(loginRequestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refreshToken(@RequestBody String refreshToken) {
        RefreshResponse refreshResponse = authenticationService.refresh(refreshToken);
        return ResponseEntity.ok(refreshResponse);
    }

}
