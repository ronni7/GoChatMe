package hello.services;

import hello.entities.InvitationMessage;
import hello.entities.InvitationMessageOutput;
import hello.entities.Message;
import hello.entities.MessageOutputDTO;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ChatServiceImpl implements ChatService {
    private UserServiceImpl userService;
    public ChatServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    public MessageOutputDTO dispatchMessage(Message message) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new MessageOutputDTO(message.getFrom(), message.getText(), time);
    }

    @Override
    public MessageOutputDTO sendGreeting(SimpMessageHeaderAccessor headerAccessor) {
        String username = headerAccessor.getNativeHeader("username").get(0);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new MessageOutputDTO("Server", username + " has joined channel", time);
    }

    @Override
    public InvitationMessageOutput dispatchInvitation(long senderID, InvitationMessage invitationMessage) {
        String from=userService.getNicknameByUserID(senderID);
        long receiverID= userService.getUserIDByNickname(invitationMessage.getReceiver());
        return new InvitationMessageOutput(from,invitationMessage.getToken(),String.valueOf(receiverID));
    }

}
