package hello.services;

import hello.DTO.MessageOutputDTO;
import hello.TO.InvitationMessageOutputTO;
import hello.TO.InvitationMessageTO;
import hello.TO.MessageTO;
import hello.entities.MessageOutput;
import hello.entities.PrivateMessageOutput;
import hello.repositories.ChannelRepository;
import hello.repositories.MessageRepository;
import hello.repositories.PrivateChannelRepository;
import hello.repositories.PrivateMessageRepository;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ChatServiceImpl implements ChatService {
    private UserServiceImpl userService;
    private MessageRepository messageRepository;
    private PrivateMessageRepository privateMessageRepository;
    private ChannelRepository channelRepository;
    private PrivateChannelRepository privateChannelRepository;

    public ChatServiceImpl(UserServiceImpl userService, MessageRepository messageRepository, PrivateMessageRepository privateMessageRepository, ChannelRepository channelRepository, PrivateChannelRepository privateChannelRepository) {
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.privateMessageRepository = privateMessageRepository;
        this.channelRepository = channelRepository;
        this.privateChannelRepository = privateChannelRepository;
    }


    @Override
    public MessageOutputDTO dispatchMessage(MessageTO messageTO, Long channelID) {
        String time = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
        messageRepository.save(new MessageOutput(messageTO.getFrom(), messageTO.getText(), time, channelRepository.getChannelByChannelID(1L)));
        return new MessageOutputDTO(messageTO.getFrom(), messageTO.getText(), time.substring(time.length() - 5));
    }

    @Override
    public MessageOutputDTO dispatchPrivateMessage(MessageTO messageTO, String token) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        privateMessageRepository.save(new PrivateMessageOutput(messageTO.getFrom(), messageTO.getText(), time, privateChannelRepository.findByToken(token)));
        return new MessageOutputDTO(messageTO.getFrom(), messageTO.getText(), time);
    }

    @Override
    public MessageOutputDTO sendGreeting(SimpMessageHeaderAccessor headerAccessor) {
        String username = headerAccessor.getNativeHeader("username").get(0);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new MessageOutputDTO("Server", username + " has joined channel", time);
    }

    @Override
    public InvitationMessageOutputTO dispatchInvitation(long senderID, InvitationMessageTO invitationMessageTO) {
        String from = userService.getNicknameByUserID(senderID);
        long receiverID = userService.getUserIDByNickname(invitationMessageTO.getReceiver());
        return new InvitationMessageOutputTO(from, invitationMessageTO.getToken(), String.valueOf(receiverID));
    }

}
