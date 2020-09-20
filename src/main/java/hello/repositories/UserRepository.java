package hello.repositories;

import hello.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByLoginAndPassword(String login, char[] password);

    List<User> findAll();

    List<User> findByLogin(String login);

    List<User> findByNicknameContains(String nickname);

    List<User> findByNickname(String nickname);

}
