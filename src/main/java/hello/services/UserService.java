package hello.services;

import hello.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User registerNewUser(User u);

    Long getUserIDByNickname(String nickname);

    boolean logUserIn(String login, char[] password);

    String getNicknameByUserID(long parseInt);
}
