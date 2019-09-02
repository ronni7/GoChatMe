package hello.repositories;

import hello.entities.PrivateChannel;
import org.springframework.data.repository.CrudRepository;

public interface PrivateChannelRepository extends CrudRepository<PrivateChannel, Long> {
}
