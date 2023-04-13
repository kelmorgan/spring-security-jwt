package com.kelmorgan.springsecurityjwt.service.impl;

import com.kelmorgan.springsecurityjwt.config.JwtService;
import com.kelmorgan.springsecurityjwt.model.AuthenticationRequest;
import com.kelmorgan.springsecurityjwt.model.AuthenticationResponse;
import com.kelmorgan.springsecurityjwt.model.RegisterRequest;
import com.kelmorgan.springsecurityjwt.repository.UserRepository;
import com.kelmorgan.springsecurityjwt.service.AuthenticationService;
import com.kelmorgan.springsecurityjwt.user.Role;
import com.kelmorgan.springsecurityjwt.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),request.getPassword()
        ));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
