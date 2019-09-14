package hello.controllers;


import hello.DTO.MessageOutputDTO;
import hello.TO.InvitationMessageTO;
import hello.TO.MessageTO;
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
    public MessageOutputDTO dispatchMessage(@DestinationVariable String roomID, MessageTO message) {
        return chatService.dispatchMessage(message, Long.valueOf(roomID));
    }

    @MessageMapping("/chat/private/{token}")
    @SendTo("/chatroom/private/{token}/")
    public MessageOutputDTO dispatchPrivateMessage(@DestinationVariable String token, MessageTO message) {
        return chatService.dispatchPrivateMessage(message, token);
    }

    @SubscribeMapping("/chatroom/{roomID}/")
    @SendTo("/chatroom/{roomID}/")
    public MessageOutputDTO addUser(SimpMessageHeaderAccessor headerAccessor) {
        return chatService.sendGreeting(headerAccessor);
    }

    @SubscribeMapping("/chatroom/private/{token}/")
    @SendTo("/chatroom/private/{token}/")
    public MessageOutputDTO addUserToPrivateChat(SimpMessageHeaderAccessor headerAccessor) {
        return chatService.sendGreeting(headerAccessor);
    }

    @MessageMapping("/chat/notifications/{senderID}")
    @SendTo("/chatroom/notifications/{receiverID}/")
    public void dispatchInvitation(@DestinationVariable long senderID, InvitationMessageTO invitationmessage) {
        long receiverID = userService.getUserIDByNickname(invitationmessage.getReceiver());
        simpMessagingTemplate.convertAndSend("/chatroom/notifications/" + receiverID + "/", chatService.dispatchInvitation(senderID, invitationmessage));

    }
}
