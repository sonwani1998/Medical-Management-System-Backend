package com.eb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eb.Entity.RegistrationResponseModel;
import com.eb.Entity.User;
import com.eb.Repository.UserRepository;
import com.eb.exception.UserNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<RegistrationResponseModel> loginUser(@RequestBody User user) {
        User foundUser = userRepository.findByUserId(user.getUserId());
        RegistrationResponseModel response = new RegistrationResponseModel("Login Successfully",user.getName(),
				user.getUserId());
        if (foundUser != null && foundUser.getMobile().equals(user.getMobile())) {
        	return ResponseEntity.created(null).body(response);
        } else {
            throw new UserNotFoundException("User not found or invalid credentials");
        }
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    
}
