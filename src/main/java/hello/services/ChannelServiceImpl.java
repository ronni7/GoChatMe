package hello.services;

import hello.entities.Channel;
import hello.entities.MessageOutput;
import hello.entities.MessageOutputDTO;
import hello.repositories.ChannelRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChannelServiceImpl implements ChannelService {
    private ChannelRepository channelRepository;


    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public List<Channel> getChannels() {
        return channelRepository.findAll();
    }

    @Override
    public List<MessageOutputDTO> getChannelMessages(int channelID) {
        return convertToDTO(channelRepository.findById(channelID).get().getMessageList());

    }

    private List<MessageOutputDTO> convertToDTO(List<MessageOutput> messageList) {
        List<MessageOutputDTO> list = new ArrayList<>();
        messageList.forEach(messageOutput -> list.add(new MessageOutputDTO(messageOutput.getSender(), messageOutput.getText(), messageOutput.getTime())));
        return list;
    }


}
