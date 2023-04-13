package com.kelmorgan.springsecurityjwt.service;

import com.kelmorgan.springsecurityjwt.model.AuthenticationRequest;
import com.kelmorgan.springsecurityjwt.model.AuthenticationResponse;
import com.kelmorgan.springsecurityjwt.model.RegisterRequest;

public interface AuthenticationService {


    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
