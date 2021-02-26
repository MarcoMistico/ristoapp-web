package com.ristoapp.services;

import org.springframework.http.ResponseEntity;

import com.ristoapp.request.LoginRequest;
import com.ristoapp.request.SignupRequest;

public interface AuthService {
	
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
	
	public ResponseEntity<?> registerUser(SignupRequest signUpRequest);
	
	public ResponseEntity<?> authenticateUser();

}
