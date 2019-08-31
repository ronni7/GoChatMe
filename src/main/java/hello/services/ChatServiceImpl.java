package hello.services;

import hello.entities.Message;
import hello.entities.MessageOutputDTO;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ChatServiceImpl implements ChatService {

    public ChatServiceImpl() {
    }


    @Override
    public MessageOutputDTO dispatchMessage(Message message) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new MessageOutputDTO(message.getFrom(), message.getText(), time);
    }

    @Override
    public MessageOutputDTO sendGreeting(SimpMessageHeaderAccessor headerAccessor) {
        String username = headerAccessor.getNativeHeader("username").get(0);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new MessageOutputDTO("Server", username + " has joined channel", time);
    }
}
