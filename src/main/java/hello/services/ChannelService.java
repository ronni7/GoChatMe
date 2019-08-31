package hello.services;

import hello.entities.Channel;
import hello.entities.MessageOutputDTO;

import java.util.List;

public interface ChannelService {
    List<MessageOutputDTO> getChannelMessages(int channelID);

    List<Channel> getChannels();

}
