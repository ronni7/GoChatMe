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
        if (userRepository.findByLogin(u.getLogin()).size() == 1)
            return new User();
        u.setPassword(BCrypt.hashpw(String.valueOf(u.getPassword()), BCrypt.gensalt()).toCharArray());
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
}
