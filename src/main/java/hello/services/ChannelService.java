package hello.services;

import hello.entities.Channel;
import hello.entities.MessageOutputDTO;
import hello.entities.PrivateChannelTO;

import java.util.List;

public interface ChannelService {
    List<MessageOutputDTO> getChannelMessages(long channelID);

    List<Channel> getChannels();

    PrivateChannelTO createPrivateChannel(long senderID, String destinationUserNickname);
}
