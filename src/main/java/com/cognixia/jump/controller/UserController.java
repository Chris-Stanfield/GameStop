package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.AuthenticationResponse;
import com.cognixia.jump.model.Game;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;
import com.cognixia.jump.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository repo;
	
	@Autowired
	UserService service;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/user")
	public List<User> getUsers() {
		
		return service.getAllUsers();
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser( @Valid @RequestBody User user ) {
		
		user.setId(null);
		
		user.setPassword( encoder.encode( user.getPassword() ) );
		
		User created = repo.save(user);
		
		return ResponseEntity.status(201).body(created);
		
	}
	
	@PutMapping("/update/user")
	public ResponseEntity<User> updateGame(@RequestBody User user) throws ResourceNotFoundException {
		
		User updated = service.updateUser(user);
		
		return new ResponseEntity<>(updated, HttpStatus.OK);	
		
	}
	
	
	@PostMapping("/{userid}/purchase/{gameid}")
	public ResponseEntity<User> purchaseGame(@PathVariable int userid, @PathVariable int gameid) throws ResourceNotFoundException {
		
		User updated = service.purchase(userid, gameid);
		
		return new ResponseEntity<>(updated, HttpStatus.OK);	
	
	}
	
	@PostMapping("/authenticate") 
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
		
		// try to catch the exception for bad credentials, just so we can set our own message when this doesn't work
		try {
			// make sure we have a valid user by checking their username and password
			authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()) );
			
		} catch(BadCredentialsException e) {
			// provide our own message on why login didn't work
			throw new Exception("Incorrect username or password");
		}
		
		// as long as no exception was thrown, user is valid
		
		// load in the user details for that user
		final UserDetails userDetails = userDetailsService.loadUserByUsername( request.getUsername() );
		
		// generate the token for that user
		final String jwt = jwtUtil.generateTokens(userDetails);
		
		// return the token
		return ResponseEntity.status(201).body( new AuthenticationResponse(jwt) );
	}
	
	
	
	
}
















