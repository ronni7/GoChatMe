package hello.services;

import hello.entities.InvitationMessage;
import hello.entities.InvitationMessageOutput;
import hello.entities.Message;
import hello.entities.MessageOutputDTO;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface ChatService {
    MessageOutputDTO dispatchMessage(Message message);

    MessageOutputDTO sendGreeting(SimpMessageHeaderAccessor headerAccessor);

    InvitationMessageOutput dispatchInvitation(long senderID, InvitationMessage invitationMessage);
}
