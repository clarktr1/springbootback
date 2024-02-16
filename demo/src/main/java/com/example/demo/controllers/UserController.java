package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/test")
    public Iterable<User> getTest() {
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        Optional<User> optionalUser = this.userRepository.findByUserName(userName);

        return optionalUser.map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());

        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User name already exists. Please try another.");
        }

        User newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User successfully created. ID: " + newUser.getId());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/update/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User user) {
        Optional<User> existingUserOp = userRepository.findByUserName(username);

        if (existingUserOp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No user found with that username. Please try again.");
        }

        User updatedUser = existingUserOp.get();

        updatedUser.setCity(user.getCity());
        updatedUser.setZipCode(user.getZipCode());
        updatedUser.setState(user.getState());
        updatedUser.setDairyAllergy(user.isDairyAllergy());
        updatedUser.setPeanutAllergy(user.isPeanutAllergy());
        updatedUser.setEggAllergy(user.isEggAllergy());

        this.userRepository.save(updatedUser);

        return ResponseEntity.status(HttpStatus.OK)
                .body("User successfully updated. ID: " + updatedUser.getId());
    }

    @CrossOrigin(origins = "https://localhost:3000")
    @DeleteMapping(path = "/delete/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName) {
        try {
            userRepository.deleteByUserName(userName);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting user: " + e.getMessage());
        }
    }

}

