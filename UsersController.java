package de.telran.myshop.HW_28_08;

import de.telran.myshop.controllers.RestController;
import de.telran.myshop.entity.User;
import de.telran.myshop.errors.UserException;
import de.telran.myshop.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsersController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/users")
    public User createUser(
            @RequestBody User userRequest
    ) {

        logger.info("createUser login: {}, password: {}", userRequest.getLogin(), userRequest.getPassword());

        return userRepository.save(userRequest);
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(
            @PathVariable Long id
    ) {
        if (!userRepository.existsById(id)) {
            throw new UserException("User with " + id + " not found", id);
        }
        return userRepository.findById(id);
    }
}