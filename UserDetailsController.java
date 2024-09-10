package de.telran.myshop.HW_28_08;

import de.telran.myshop.controllers.RestController;
import de.telran.myshop.entity.User;
import de.telran.myshop.entity.UserDetail;
import de.telran.myshop.errors.UserDetailException;
import de.telran.myshop.errors.UserException;
import de.telran.myshop.repository.UserDetailsRepository;
import de.telran.myshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserDetailsController {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/{userId}/details")
    public UserDetail createUserDetail(
            @PathVariable Long userId,
            @RequestBody UserDetail userDetailRequest
    ) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserException("User with id " + userId + " not found", userId)
        );
        userDetailRequest.setUser(user);
        return userDetailsRepository.save(userDetailRequest);
    }

    @GetMapping("/users/details/{id}")
    public UserDetail getUserDetailsById(
            @PathVariable Long id
    ) {
        UserDetail userDetail = userDetailsRepository.findById(id).orElseThrow(
                () -> new UserDetailException("UserDetail with id " + id + " not found", id)
        );

        return userDetail;
    }
}
