package gochatme.controllers;

import gochatme.DTO.MessageOutputDTO;
import gochatme.TO.*;
import gochatme.services.ChatServiceImpl;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatServiceImpl chatService;

    public ChatController(ChatServiceImpl chatService, SimpMessagingTemplate simpMessagingTemplate) {
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat/{roomID}")
    @SendTo("/chatroom/{roomID}")
    public MessageOutputDTO dispatchMessage(@DestinationVariable String roomID, MessageTO message) {
        return chatService.dispatchMessage(message, Long.valueOf(roomID));
    }

    @MessageMapping("/chat/private/{token}")
    @SendTo("/chatroom/private/{token}")
    public MessageOutputDTO dispatchPrivateMessage(@DestinationVariable String token, MessageTO message) {
        return chatService.dispatchPrivateMessage(message, token);
    }

    @SubscribeMapping("/chatroom/{roomID}")
    @SendTo("/chatroom/{roomID}")
    public MessageOutputDTO addUser(SimpMessageHeaderAccessor headerAccessor) {
        return chatService.sendGreeting(headerAccessor);
    }

    @SubscribeMapping("/chatroom/private/{token}")
    @SendTo("/chatroom/private/{token}")
    public MessageOutputDTO addUserToPrivateChat(SimpMessageHeaderAccessor headerAccessor) {
        return chatService.sendGreeting(headerAccessor);
    }

    @MessageMapping("/chat/notifications/{senderID}")
    @SendTo("/chatroom/notifications/{receiverID}")
    public void dispatchInvitation(@DestinationVariable long senderID, InvitationMessageTO invitationMessageTO) {
        InvitationMessageOutputTO invitationMessageOutputTO = chatService.dispatchInvitation(senderID, invitationMessageTO);
        simpMessagingTemplate.convertAndSend("/chatroom/notifications/" + invitationMessageOutputTO.getReceiverID() + "/", invitationMessageOutputTO);
    }

    @MessageMapping("/chat/notifications/accepted/{senderID}")
    @SendTo("/chatroom/notifications/{receiverID}")
    public void dispatchInvitationAccepted(InvitationAcceptedMessageTO invitationAcceptedMessageTO) {
        InvitationAcceptedOutputMessageTO invitationAcceptedOutputMessageTO = chatService.dispatchAcceptedInvitation(invitationAcceptedMessageTO);
        simpMessagingTemplate.convertAndSend("/chatroom/notifications/" + invitationAcceptedOutputMessageTO.getReceiverID() + "/", invitationAcceptedOutputMessageTO);
    }

}
