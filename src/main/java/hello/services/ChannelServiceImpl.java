package hello.services;

import hello.entities.*;
import hello.repositories.ChannelRepository;
import hello.repositories.PrivateChannelRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
        System.out.println("senderID = " + senderID);
       long user2ID= userService.getUserIDByNickname(destinationUserNickname);
        for (PrivateChannel p : privateChannelRepository.findAll())
            /*if (BCrypt.checkpw(senderID + user2ID, String.valueOf(p.getToken())))*/ //TODO encrypted chatroom token handling
            if(("token"+senderID+user2ID).equals(p.getToken()))
                return new PrivateChannelTO(p.getToken(), true);
        PrivateChannel privateChannel = new PrivateChannel();
        String token = "token"+senderID+user2ID;
        privateChannel.setToken(token);
        return new PrivateChannelTO(privateChannelRepository.save(privateChannel).getToken(), false);
    }

    @Override
    public List<MessageOutputDTO> getChannelMessages(long channelID) {
        return convertToDTO(channelRepository.findById(channelID).get().getMessageList());

    }

    private List<MessageOutputDTO> convertToDTO(List<MessageOutput> messageList) {
        List<MessageOutputDTO> list = new ArrayList<>();
        messageList.forEach(messageOutput -> list.add(new MessageOutputDTO(messageOutput.getSender(), messageOutput.getText(), messageOutput.getTime())));
        return list;
    }


}
