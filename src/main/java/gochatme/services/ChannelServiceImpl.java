package gochatme.services;

import gochatme.DTO.MessageOutputDTO;
import gochatme.TO.PrivateChannelTO;
import gochatme.entities.Channel;
import gochatme.entities.MessageOutput;
import gochatme.entities.PrivateChannel;
import gochatme.entities.PrivateMessageOutput;
import gochatme.exceptions.ResourceNotFoundException;
import gochatme.repositories.ChannelRepository;
import gochatme.repositories.PrivateChannelRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChannelServiceImpl implements ChannelService {
    private ChannelRepository channelRepository;
    private UserServiceImpl userService;
    private PrivateChannelRepository privateChannelRepository;

    public ChannelServiceImpl(ChannelRepository channelRepository, UserServiceImpl userServiceImpl, PrivateChannelRepository privateChannelRepository) {
        this.channelRepository = channelRepository;
        this.userService = userServiceImpl;
        this.privateChannelRepository = privateChannelRepository;
    }

    @Override
    public List<Channel> getChannels() {
        return (List<Channel>) channelRepository.findAll();
    }

    @Override
    public PrivateChannelTO createPrivateChannel(long senderID, String destinationUserNickname) {
        long user2ID = userService.getUserIDByNickname(destinationUserNickname);
        if (senderID > user2ID) {
            long temp = senderID;
            senderID = user2ID;
            user2ID = temp;
        }
        final String token = "token" + senderID + user2ID;
        Optional<PrivateChannel> optional = privateChannelRepository.findByToken(token);
        PrivateChannel privateChannel;
        if (optional.isPresent()) {
            privateChannel = optional.get();
        } else {
            privateChannel = new PrivateChannel();
            privateChannel.setToken(token);
            privateChannel.setAccepted(false);
            privateChannel = privateChannelRepository.save(privateChannel);
        }
        return new PrivateChannelTO(privateChannel.getChannelID(), privateChannel.getToken(), privateChannel.getAccepted(), convertPrivateToDTO(privateChannel.getMessageList()));
    }

    @Override
    public void accept(String token) {
        PrivateChannel privateChannel = privateChannelRepository.findByToken(token).orElseThrow((ResourceNotFoundException::new));
        privateChannel.setAccepted(true);
        privateChannelRepository.save(privateChannel);
    }

    @Override
    public List<MessageOutputDTO> getChannelMessages(long channelID) {
        return convertToDTO(channelRepository.findById(channelID).get().getMessageList());

    }

    @Override
    public List<MessageOutputDTO> getPrivateChannelMessages(long channelID) {
        return convertPrivateToDTO(privateChannelRepository.findById(channelID).get().getMessageList());

    }

    @Override
    public Channel addChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public List<Channel> getChannelsByName(String name) {
        return channelRepository.findChannelByNameContains(name);
    }

    private List<MessageOutputDTO> convertPrivateToDTO(List<PrivateMessageOutput> messageList) {
        List<MessageOutputDTO> list = new ArrayList<>();
        messageList.forEach(messageOutput -> list.add(new MessageOutputDTO(messageOutput.getSender(), messageOutput.getText(), messageOutput.getTime())));
        return list;
    }

    private List<MessageOutputDTO> convertToDTO(List<MessageOutput> messageList) {
        List<MessageOutputDTO> list = new ArrayList<>();
        messageList.forEach(messageOutput -> list.add(new MessageOutputDTO(messageOutput.getSender(), messageOutput.getText(), messageOutput.getTime())));
        return list;
    }


}
