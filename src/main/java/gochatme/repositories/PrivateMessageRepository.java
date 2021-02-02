package gochatme.repositories;

import gochatme.entities.PrivateMessageOutput;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrivateMessageRepository extends CrudRepository<PrivateMessageOutput, Long> {
    List<PrivateMessageOutput> findAll();
}
