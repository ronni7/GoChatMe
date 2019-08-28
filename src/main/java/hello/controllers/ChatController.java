package hello.controllers;


import hello.entities.Message;
import hello.entities.MessageOutput;
import hello.services.ChatServiceImpl;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin(origins = "/*")
public class ChatController {
    private final ChatServiceImpl chatService;

    public ChatController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @GetMapping(path = "/messages")
    public @ResponseBody
    List<Message> getAllUsers(@RequestParam int channelID) {
        return chatService.getChannelMessages(channelID);
    }

    @MessageMapping("/chat/{roomID}")
    @SendTo("/chatroom/{roomID}/")
    public MessageOutput dispatchMessage(@DestinationVariable String roomID,Message message) {
        return chatService.dispatchMessage(message);
    }

    @SubscribeMapping("/chatroom/{roomID}/")
    @SendTo("/chatroom/{roomID}/")
    public MessageOutput addUser(SimpMessageHeaderAccessor headerAccessor) {
        return chatService.sendGreeting(headerAccessor);
    }

}
