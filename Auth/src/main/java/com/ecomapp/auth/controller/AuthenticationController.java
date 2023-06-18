package com.ecomapp.auth.controller;

import com.ecomapp.auth.models.AuthenticationResponse;
import com.ecomapp.auth.models.CustomerRequest;
import com.ecomapp.auth.models.CustomerSignUp;
import com.ecomapp.auth.models.RefreshTokenResponce;
import com.ecomapp.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")

public class AuthenticationController {

    @Autowired
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody CustomerSignUp request
    ) {
        try {
            return ResponseEntity.ok(service.register(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody CustomerRequest request) {
        try {
            AuthenticationResponse authenticationResponse = service.authenticate(request);
            return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("invalid acces");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponce> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        try {
            RefreshTokenResponce refreshTokenResponce = service.refreshToken(request, response);
            return new ResponseEntity<>(refreshTokenResponce,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}