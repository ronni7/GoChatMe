package gochatme.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import gochatme.entities.SocialUser;
import gochatme.entities.User;
import gochatme.requestBody.LoginRequestBody;
import gochatme.services.UserServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/goChatMe")

public class UserController {

    private final UserServiceImpl userService;
    private final ObjectMapper objectMapper;

    public UserController(UserServiceImpl userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @GetMapping(path = "/users")
    public @ResponseBody
    List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/usersByName")
    public @ResponseBody
    List<User> getUsersByName(@RequestParam String name) {
        return userService.findUsersByName(name);
    }

    @PostMapping(path = "/login")
    public @ResponseBody
    User logUserIn(@RequestBody LoginRequestBody loginRequestBody) {
        if (loginRequestBody != null && loginRequestBody.getPassword() != null) {
            return userService.logUserIn(loginRequestBody.getLogin(), loginRequestBody.getPassword().toCharArray());
        }
        return null;
    }

    @PostMapping(path = "/register")
    public @ResponseBody
    User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PostMapping(path = "/verifyExternalAccount")
    public @ResponseBody
    User verifyExternalAccount(@RequestBody SocialUser socialUser) {
        return userService.verifyExternalAccount(socialUser);
    }

}
