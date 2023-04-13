package com.kelmorgan.springsecurityjwt.auth;

import com.kelmorgan.springsecurityjwt.model.AuthenticationResponse;
import com.kelmorgan.springsecurityjwt.model.AuthenticationRequest;
import com.kelmorgan.springsecurityjwt.model.RegisterRequest;
import com.kelmorgan.springsecurityjwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping
    public AuthenticationResponse register(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
