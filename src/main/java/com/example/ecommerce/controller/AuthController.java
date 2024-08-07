package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.AuthenticationRequest;
import com.example.ecommerce.dto.request.SignUpRequest;
import com.example.ecommerce.dto.response.AuthenticationResponse;
import com.example.ecommerce.dto.response.UserResponse;
import com.example.ecommerce.service.AuthService;
import com.nimbusds.jose.KeyLengthException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

    @PostMapping("/login")
    public AuthenticationResponse signin(@RequestBody AuthenticationRequest request) throws KeyLengthException {
        return authService.signin(request);
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody SignUpRequest request){
        return authService.createUser(request);
    }
}
