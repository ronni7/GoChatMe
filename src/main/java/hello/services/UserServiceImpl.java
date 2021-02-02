package hello.services;

import hello.entities.SocialUser;
import hello.entities.User;
import hello.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User registerNewUser(User user) {
        if (userRepository.findByLogin(user.getLogin()).size() == 1 || userRepository.findByNickname(user.getNickname()).size() == 1)
            return new User();
        user.setPassword(BCrypt.hashpw(String.valueOf(user.getPassword()), BCrypt.gensalt()).toCharArray());
        return userRepository.save(user);

    }

    @Override
    public Long getUserIDByNickname(String nickname) {
        List<User> userList = userRepository.findByNickname(nickname);
        if (userList.size() == 1)
            return userList.get(0).getId();
        return -1L;
    }

    @Override
    public User logUserIn(String login, char[] password) {
        ArrayList<User> list = new ArrayList<>(userRepository.findByLogin(login));
        for (User user : list)
            if (BCrypt.checkpw(String.valueOf(password), String.valueOf(user.getPassword())))
                return user;
        return null;
    }

    @Override
    public String getNicknameByUserID(long userID) {
        return userRepository.findById(userID).get().getNickname();
    }

    @Override
    public List<User> findUsersByName(String name) {
        return userRepository.findByNicknameContains(name);
    }

    @Override
    public User verifyExternalAccount(SocialUser socialUser) {
        ArrayList<User> list = new ArrayList<>(userRepository.findByLogin(socialUser.getEmail()));
        for (User user : list) {
            if (user.equalsSocialUser(socialUser)) {
                user.synchronize(socialUser);
                return user;
            }
        }
        User registeredUser = socialUser.toUser();
        registeredUser.setPassword(BCrypt.hashpw(registeredUser.getEmail(), BCrypt.gensalt()).toCharArray());
        return userRepository.save(registeredUser);
    }
}
