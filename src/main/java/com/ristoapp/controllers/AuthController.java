package com.ristoapp.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ristoapp.request.LoginRequest;
import com.ristoapp.request.SignupRequest;
import com.ristoapp.services.AuthService;

@RestController
@RequestMapping("/api/${app.version}/auth")
public class AuthController {
	@Autowired
	AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return authService.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return authService.registerUser(signUpRequest);
	}

	@GetMapping("/anonymousToken")
	public ResponseEntity<?> authenticateUser() {
		return authService.authenticateUser();
	}
}
