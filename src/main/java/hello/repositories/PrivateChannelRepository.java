package hello.repositories;

import hello.entities.PrivateChannel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrivateChannelRepository extends CrudRepository<PrivateChannel, Long> {
    List<PrivateChannel> findAll();

    PrivateChannel findByToken(String token);
}
