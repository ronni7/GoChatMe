package hello.controllers;


import hello.entities.Message;
import hello.entities.MessageOutputDTO;
import hello.services.ChatServiceImpl;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


@Controller
@CrossOrigin(origins = "/*")
public class ChatController {
    private final ChatServiceImpl chatService;

    public ChatController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat/{roomID}")
    @SendTo("/chatroom/{roomID}/")
    public MessageOutputDTO dispatchMessage(@DestinationVariable String roomID, Message message) {

        return chatService.dispatchMessage(message);
    }

    @SubscribeMapping("/chatroom/{roomID}/")
    @SendTo("/chatroom/{roomID}/")
    public MessageOutputDTO addUser(SimpMessageHeaderAccessor headerAccessor) {
        return chatService.sendGreeting(headerAccessor);
    }

}
