package hello.controllers;


import hello.entities.Message;
import hello.entities.MessageOutput;
import hello.services.ChatServiceImpl;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @MessageMapping("/chat")
    @SendTo("/general/messages")
    public MessageOutput respondToGeneral(Message message){
       return chatService.readAndSend(message);
   }


}
