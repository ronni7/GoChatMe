package gochatme.repositories;

import gochatme.entities.MessageOutput;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MessageRepository extends CrudRepository<MessageOutput, Long> {
    List<MessageOutput> findAll();
}
