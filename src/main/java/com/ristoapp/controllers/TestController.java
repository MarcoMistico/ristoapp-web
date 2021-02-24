package com.ristoapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ristoapp.models.User;
import com.ristoapp.repository.UserRepository;
import com.ristoapp.response.MessageResponse;

@CrossOrigin(origins = "${app.domain}", maxAge = 3600)
@RestController
@RequestMapping("/api/${app.version}/guest")
public class TestController {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/test")
	public ResponseEntity<MessageResponse> getUsers() {

		return ResponseEntity.ok(new MessageResponse("Test"));
	}
}
