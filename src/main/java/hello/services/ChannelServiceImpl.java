package hello.services;

import hello.DTO.MessageOutputDTO;
import hello.TO.PrivateChannelTO;
import hello.entities.Channel;
import hello.entities.MessageOutput;
import hello.entities.PrivateChannel;
import hello.entities.PrivateMessageOutput;
import hello.repositories.ChannelRepository;
import hello.repositories.PrivateChannelRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        return channelRepository.findAll();
    }

    @Override
    public PrivateChannelTO createPrivateChannel(long senderID, String destinationUserNickname) {
        long user2ID = userService.getUserIDByNickname(destinationUserNickname);
        for (PrivateChannel p : privateChannelRepository.findAll())
            /*if (BCrypt.checkpw(senderID + user2ID, String.valueOf(p.getToken())))*/ //TODO encrypted chatroom token handling
            if (("token" + senderID + user2ID).equals(p.getToken()))
                return new PrivateChannelTO(p.getChannelID(), p.getToken(), true);
        PrivateChannel privateChannel = new PrivateChannel();
        System.out.println("privateChannel = " + privateChannel);
        String token = "token" + senderID + user2ID;
        privateChannel.setToken(token);
        privateChannel = privateChannelRepository.save(privateChannel);
        return new PrivateChannelTO(privateChannel.getChannelID(), privateChannel.getToken(), false);
    }

    @Override
    public List<MessageOutputDTO> getChannelMessages(long channelID) {
        /*String time = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date()); todo
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy HH:mm");*/
        return convertToDTO(channelRepository.findById(channelID).get().getMessageList());

    }

    @Override
    public List<MessageOutputDTO> getPrivateChannelMessages(long channelID) {
        return convertPrivateToDTO(privateChannelRepository.findById(channelID).get().getMessageList());

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
