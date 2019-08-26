package hello.services;

import hello.entities.Message;
import hello.entities.MessageOutput;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.List;

public interface ChatService {
    List<Message> getChannelMessages(int channelID);
    MessageOutput readAndSend(Message message) ;
    MessageOutput sendGreeting(SimpMessageHeaderAccessor headerAccessor);
}
