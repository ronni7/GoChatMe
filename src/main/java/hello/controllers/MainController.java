package hello.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.entities.User;
import hello.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/goChatMe")

public class MainController {

    private final UserServiceImpl userService;
    private final ObjectMapper objectMapper;

    public MainController(UserServiceImpl userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @GetMapping(path = "/hello")
    public @ResponseBody
    String hello() {
        return "Hello World";
    }

    @Deprecated
    @GetMapping(path = "/all")
    public @ResponseBody
    List<User> getAllUsers() {
        return userService.findAll();
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
    User logUserIn(@RequestParam String password, @RequestParam String login) {

        return userService.logUserIn(login, password.toCharArray());
    }

    @PostMapping(path = "/register")
    public @ResponseBody
    User registerNewUser(@RequestBody User u) {
        return userService.registerNewUser(u);
    }

}
