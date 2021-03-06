package gochatme.services;

import gochatme.entities.SocialUser;
import gochatme.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User registerNewUser(User user);

    Long getUserIDByNickname(String nickname);

    User logUserIn(String login, char[] password);

    String getNicknameByUserID(long parseInt);

    List<User> findUsersByName(String name);

    User verifyExternalAccount(SocialUser socialUser);
}
