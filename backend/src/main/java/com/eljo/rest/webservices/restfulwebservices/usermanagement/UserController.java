package com.eljo.rest.webservices.restfulwebservices.usermanagement;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user) {
        if (userRepository.findById(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists! Choose another username to register.");
        }

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }

    @PutMapping("/users/{username}/password")
    public ResponseEntity<String> changePassword(@PathVariable String username, @RequestBody String newPassword) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return ResponseEntity.ok("Password changed successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}