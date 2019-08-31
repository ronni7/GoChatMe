package hello.repositories;

import hello.entities.Channel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ChannelRepository extends CrudRepository<Channel, Integer> {
    List<Channel> findAll();
}
