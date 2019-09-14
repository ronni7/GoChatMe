package hello.services;

import hello.TO.InvitationMessageTO;
import hello.TO.InvitationMessageOutputTO;
import hello.TO.MessageTO;
import hello.DTO.MessageOutputDTO;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface ChatService {
    MessageOutputDTO dispatchMessage(MessageTO messageTO, Long channelID);

    MessageOutputDTO dispatchPrivateMessage(MessageTO messageTO, String token);

    MessageOutputDTO sendGreeting(SimpMessageHeaderAccessor headerAccessor);

    InvitationMessageOutputTO dispatchInvitation(long senderID, InvitationMessageTO invitationMessageTO);
}
