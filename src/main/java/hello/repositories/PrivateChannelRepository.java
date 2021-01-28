package hello.repositories;

import hello.entities.PrivateChannel;
import hello.entities.PrivateMessageOutput;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PrivateChannelRepository extends CrudRepository<PrivateChannel, Long> {
    List<PrivateChannel> findAll();

    Optional<PrivateChannel> findByToken(String token);

}
