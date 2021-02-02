package hello.services;

import hello.entities.Channel;
import hello.DTO.MessageOutputDTO;
import hello.TO.PrivateChannelTO;

import java.util.List;

public interface ChannelService {
    List<MessageOutputDTO> getChannelMessages(long channelID);

    List<Channel> getChannels();

    PrivateChannelTO createPrivateChannel(long senderID, String destinationUserNickname);

    void accept(String token);

    List<MessageOutputDTO> getPrivateChannelMessages(long channelID);

    Channel addChannel(Channel channel);

    List<Channel> getChannelsByName(String name);
}
