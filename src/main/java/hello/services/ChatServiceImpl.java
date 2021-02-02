package hello.services;

import hello.DTO.MessageOutputDTO;
import hello.TO.*;
import hello.entities.MessageOutput;
import hello.entities.PrivateMessageOutput;
import hello.exceptions.ResourceNotFoundException;
import hello.repositories.ChannelRepository;
import hello.repositories.MessageRepository;
import hello.repositories.PrivateChannelRepository;
import hello.repositories.PrivateMessageRepository;
import hello.utilities.enums.NotificationType;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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
        privateChannelRepository.findByToken(token).map(privateChannel -> privateMessageRepository.save(
                new PrivateMessageOutput(messageTO.getFrom(),
                        messageTO.getText(),
                        time,
                        privateChannel))).orElseThrow(ResourceNotFoundException::new);
        return new MessageOutputDTO(messageTO.getFrom(), messageTO.getText(), time);
    }

    @Override
    public MessageOutputDTO sendGreeting(SimpMessageHeaderAccessor headerAccessor) {
        return Optional.ofNullable(headerAccessor.getNativeHeader("username")).map(strings -> strings.stream().findFirst().map(username ->
                new MessageOutputDTO(
                        "Server",
                        username + " has joined channel",
                        new SimpleDateFormat("HH:mm").format(new Date())
                ))
                .orElseThrow(ResourceNotFoundException::new)).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public InvitationMessageOutputTO dispatchInvitation(long senderID, InvitationMessageTO invitationMessageTO) {
        return new InvitationMessageOutputTO(
                userService.getNicknameByUserID(senderID),
                invitationMessageTO.getToken(),
                String.valueOf(userService.getUserIDByNickname(invitationMessageTO.getReceiver())), NotificationType.INVITATION);
    }

    @Override
    public InvitationAcceptedOutputMessageTO dispatchAcceptedInvitation(InvitationAcceptedMessageTO invitationAcceptedMessageTO) {
        return new InvitationAcceptedOutputMessageTO(userService.getNicknameByUserID(invitationAcceptedMessageTO.getSenderID()),
                invitationAcceptedMessageTO.getToken(),
                userService.getUserIDByNickname(invitationAcceptedMessageTO.getReceiver()), invitationAcceptedMessageTO.getType());
    }
}
