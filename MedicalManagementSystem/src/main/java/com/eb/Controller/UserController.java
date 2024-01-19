package com.eb.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eb.Entity.Medicine;
import com.eb.Entity.RegistrationResponseModel;
import com.eb.Entity.User;
import com.eb.Repository.UserRepository;
import com.eb.exception.UserNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<RegistrationResponseModel> registerUser(@RequestBody User user) {
		user = userRepository.save(user);
		RegistrationResponseModel response = new RegistrationResponseModel("Registered successfully", user.getName(),
				user.getUserId());
		return ResponseEntity.created(null).body(response);
	}
	
	@GetMapping("/profile/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("userId not Found "));
        
		return ResponseEntity.created(null).body(user);
    }

}
