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

    public ChannelServiceImpl(ChannelRepository channelRepository, PrivateChannelRepository privateChannelRepository) {
        this.channelRepository = channelRepository;
        this.privateChannelRepository = privateChannelRepository;
    }

    private PrivateChannelRepository privateChannelRepository;


    @Override
    public List<Channel> getChannels() {
        return channelRepository.findAll();
    }

    @Override
    public PrivateChannelTO createPrivateChannel(String user1ID, String user2ID) {

        for (PrivateChannel p : privateChannelRepository.findAll())
            if (BCrypt.checkpw(user1ID + user2ID, String.valueOf(p.getToken())))
                return new PrivateChannelTO(p.getToken(), true);

        PrivateChannel privateChannel = new PrivateChannel();
        String token = BCrypt.hashpw(user1ID + user2ID, BCrypt.gensalt());
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
