package gochatme.repositories;

import gochatme.entities.Channel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ChannelRepository extends CrudRepository<Channel, Long> {
    List<Channel> findChannelByNameContains(String name);

    Channel getChannelByChannelID(Long channelID);
}
