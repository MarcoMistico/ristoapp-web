package com.ristoapp.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ristoapp.enums.RoleEnum;
import com.ristoapp.jwt.JwtUtils;
import com.ristoapp.models.Role;
import com.ristoapp.models.User;
import com.ristoapp.repository.RoleRepository;
import com.ristoapp.repository.UserRepository;
import com.ristoapp.request.LoginRequest;
import com.ristoapp.request.SignupRequest;
import com.ristoapp.response.JwtResponse;
import com.ristoapp.response.MessageResponse;
import com.ristoapp.security.services.UserDetailsImpl;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Value("${app.usernameError}")
	private String usernameError;
	
	@Value("${app.emailError}")
	private String emailError;
	
	@Value("${app.roleError}")
	private String roleError;
	
	@Value("${app.userCreated}")
	private String userCreated;
	
	@Value("${app.userUpdated}")
	private String userUpdated;
	
	@Value("${app.userDeleted}")
	private String userDeleted;

	@Override
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication, false);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 userDetails.getName(), 
				 userDetails.getSurname(), 
				 userDetails.getAge(), 
				 userDetails.getPhone(),
				 roles));
	}

	@Override
	public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(usernameError));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(emailError));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getName(),
							 signUpRequest.getSurname(),
							 signUpRequest.getAge(),
							 signUpRequest.getPhone());

		Set<Integer> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role guestRole = roleRepository.findByName(RoleEnum.ROLE_GUEST)
					.orElseThrow(() -> new RuntimeException(roleError));
			roles.add(guestRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case 3:
					Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException(roleError));
					roles.add(adminRole);

					break;
				case 2:
					Role waiterRole = roleRepository.findByName(RoleEnum.ROLE_WAITER)
							.orElseThrow(() -> new RuntimeException(roleError));
					roles.add(waiterRole);

					break;
				default:
					Role guestRole = roleRepository.findByName(RoleEnum.ROLE_GUEST)
							.orElseThrow(() -> new RuntimeException(roleError));
					roles.add(guestRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse(userCreated));
	}

	@Override
	public ResponseEntity<?> authenticateUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String jwt = jwtUtils.generateJwtToken(authentication, true);

		return ResponseEntity.ok(new JwtResponse(jwt));
	}

}
