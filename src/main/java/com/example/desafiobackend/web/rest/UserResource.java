package com.example.desafiobackend.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.desafiobackend.model.User;
import com.example.desafiobackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id) {
        System.out.printf("REST request to get user : {%s}%n", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        System.out.println("REST request to get All users");

        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        System.out.print("REST request to create user");

        User newUser = new User();
        newUser.setNickname(user.getNickname());
        newUser.setRegistration_date(new Timestamp(new Date().getTime()));

        var result = userRepository.save(newUser);

        return ResponseEntity.created(new URI("/api/user/" + result.getId()))
                .body(result);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws URISyntaxException {
        System.out.print("REST request to update a user");
        var result = userRepository.save(user);
        if (result.getId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        if (userRepository.findById(user.getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        userRepository.findById(user.getId()).map(u -> {
            u.setNickname(user.getNickname());
            return null;
        });
        return ResponseEntity.created(new URI("/api/user/" + result.getId()))
                .body(result);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        System.out.print("REST request to delete a user");
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}