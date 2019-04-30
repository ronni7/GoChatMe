package hello.controllers;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.entities.User;
import hello.services.UserServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(path = "/goChatMe")

public class MainController {

    private final UserServiceImpl userService;
    @Autowired
    ObjectMapper objectMapper;

    public MainController(UserServiceImpl userService) {
        this.userService = userService;

    }

    @GetMapping(path = "/hello")
    public @ResponseBody
    String hello() {
        return "Hello World" ;
    }
    @GetMapping(path = "/all")
    public @ResponseBody
    List<User> getAllUsers()
    {
        return userService.findAll();
    }

    @PostMapping(path = "/login")
    public @ResponseBody
    boolean logUserIn(@RequestParam String password,@RequestParam String login) {

    return userService.logUserIn(login,password.toCharArray());
    }

    @PostMapping(path = "/register")
    public @ResponseBody
    User registerNewUser(@RequestParam String s) {
        User u=null;
        try {

          u= objectMapper.readValue(s,User.class);
        }  catch (JsonParseException e) {
            System.out.println("it doesnt work ");
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userService.registerNewUser(u);
    }

}