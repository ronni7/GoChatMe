package hello.controllers;


import hello.entities.InvitationMessage;
import hello.entities.Message;
import hello.entities.MessageOutputDTO;
import hello.services.ChatServiceImpl;
import hello.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


@Controller
@CrossOrigin(origins = "/*")
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatServiceImpl chatService;
    private final UserServiceImpl userService;

    @Autowired
    public ChatController(ChatServiceImpl chatService, UserServiceImpl userService, SimpMessagingTemplate simpMessagingTemplate) {
        this.chatService = chatService;
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat/{roomID}")
    @SendTo("/chatroom/{roomID}/")
    public MessageOutputDTO dispatchMessage(@DestinationVariable String roomID, Message message) {
        return chatService.dispatchMessage(message);
    }

    @MessageMapping("/chat/private/{token}")
    @SendTo("/chatroom/private/{token}/")
    public MessageOutputDTO dispatchPrivateMessage(@DestinationVariable String token, Message message) {
        return chatService.dispatchMessage(message);
    }

    @SubscribeMapping("/chatroom/{roomID}/")
    @SendTo("/chatroom/{roomID}/")
    public MessageOutputDTO addUser(SimpMessageHeaderAccessor headerAccessor) {
        return chatService.sendGreeting(headerAccessor);
    }

    // TODO: 2019-09-02 Add Implementation and MQ
    @SubscribeMapping("/chatroom/private/{token}/")
    @SendTo("/chatroom/private/{token}/")
    public MessageOutputDTO addUserToPrivateChat(SimpMessageHeaderAccessor headerAccessor) {
        return chatService.sendGreeting(headerAccessor);
    }

    @MessageMapping("/chat/notifications/{senderID}")
    @SendTo("/chatroom/notifications/{receiverID}/")
    public void dispatchInvitation(@DestinationVariable long senderID, InvitationMessage invitationMessage) {
        long receiverID = userService.getUserIDByNickname(invitationMessage.getReceiver());
        simpMessagingTemplate.convertAndSend("/chatroom/notifications/" + receiverID + "/", chatService.dispatchInvitation(senderID, invitationMessage));

    }
}
