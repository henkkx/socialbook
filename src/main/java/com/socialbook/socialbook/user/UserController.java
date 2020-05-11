package com.socialbook.socialbook.user;

import com.socialbook.socialbook.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/1.0/users")
    GenericResponse createUser(@RequestBody User user) {
        userService.save(user);
        return new GenericResponse("User saved");
    }
}
