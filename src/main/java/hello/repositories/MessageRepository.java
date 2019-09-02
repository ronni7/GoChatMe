package hello.repositories;

import hello.entities.MessageOutput;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MessageRepository extends CrudRepository<MessageOutput, Long> {
    List<MessageOutput> findAll();
}
