package hello.services;

import hello.DTO.MessageOutputDTO;
import hello.TO.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface ChatService {
    MessageOutputDTO dispatchMessage(MessageTO messageTO, Long channelID);

    MessageOutputDTO dispatchPrivateMessage(MessageTO messageTO, String token);

    MessageOutputDTO sendGreeting(SimpMessageHeaderAccessor headerAccessor);

    InvitationMessageOutputTO dispatchInvitation(long senderID, InvitationMessageTO invitationMessageTO);

    InvitationAcceptedOutputMessageTO dispatchAcceptedInvitation(InvitationAcceptedMessageTO invitationAcceptedMessageTO);
}
