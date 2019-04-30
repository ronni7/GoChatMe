package hello.services;

import hello.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User registerNewUser(User u);

    boolean logUserIn(String login, char[] password);
}
