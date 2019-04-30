package hello.services;

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
      return  userRepository.findAll();
    }

    @Override
    public User registerNewUser(User u) {
        u.setPassword(BCrypt.hashpw(String.valueOf(u.getPassword()),BCrypt.gensalt()).toCharArray());
        return userRepository.save(u);
    }

    @Override
    public boolean logUserIn(String login, char[] password) {

        ArrayList<User> list = new ArrayList<>(userRepository.findByLogin(login));
        for (User u: list)
            if (BCrypt.checkpw(String.valueOf(password), String.valueOf(u.getPassword())))
                return true;
return false;
    }

  /*  private User getUserByValidationKey(String validationKey) {
        for (User user : userRepository.findAll())
            if (BCrypt.checkpw(validationKey, user.getValidationKey())) return user;
        return null;
    }

    @Override
    public boolean checkValidationKey(String validKey, String login) {
        User user = getUserByValidationKey(validKey);
        if (user != null && !user.getActivated() && user.getLogin().equals(login)) {
            user.setActivated(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public String registerNewUser(String name, String login, String nickname) {
        String generatedKey;
        KeyGenerator keyGenerator = new KeyGenerator();
        generatedKey = keyGenerator.generate(6);
        String encrypted = BCrypt.hashpw(generatedKey, BCrypt.gensalt());
        User u = new User(name, login, nickname, encrypted, false);
        userRepository.save(u);
        return generatedKey;
    }*/
}
