package gochatme.repositories;

import gochatme.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByLogin(String login);

    List<User> findByNicknameContains(String nickname);

    List<User> findByNickname(String nickname);

}
