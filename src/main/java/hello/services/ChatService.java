package hello.services;

import hello.entities.Message;
import hello.entities.MessageOutputDTO;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface ChatService {
    MessageOutputDTO dispatchMessage(Message message);

    MessageOutputDTO sendGreeting(SimpMessageHeaderAccessor headerAccessor);

}
