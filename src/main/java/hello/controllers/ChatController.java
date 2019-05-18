package hello.controllers;


import hello.entities.Message;
import hello.services.ChatServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/goChatMe/chat")
public class ChatController {
    private final ChatServiceImpl chatService;

    public ChatController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @GetMapping(path = "/messages")
    public @ResponseBody
    List<Message> getAllUsers(@RequestParam int channelID)
    {
        return chatService.getChannelMessages(channelID);
    }
}
