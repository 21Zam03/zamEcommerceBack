package com.zam.zamMarket.controller;

import com.zam.zamMarket.payload.request.SignInRequest;
import com.zam.zamMarket.payload.request.SignUpRequest;
import com.zam.zamMarket.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.API_PATH)
public class AuthController {

    public static final String API_PATH = "/api/auth";

    public final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return new ResponseEntity<>(this.authService.signIn(signInRequest), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return new ResponseEntity<>(this.authService.signUp(signUpRequest), HttpStatus.CREATED);
    }

}
