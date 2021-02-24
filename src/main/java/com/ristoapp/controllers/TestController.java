package com.ristoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ristoapp.repository.UserRepository;
import com.ristoapp.response.MessageResponse;

//@CrossOrigin(origins = "${app.domain}", maxAge = 3600)
@RestController
@RequestMapping("/api/${app.version}")
public class TestController {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/guest/test")
	public ResponseEntity<MessageResponse> getGuestTest() {

		return ResponseEntity.ok(new MessageResponse("Test Guest"));
	}
	
	@GetMapping("/waiter/test")
	@PreAuthorize("hasRole('WAITER')")
	public ResponseEntity<MessageResponse> getWaiterTest() {

		return ResponseEntity.ok(new MessageResponse("Test Waiter"));
	}
}
