package hello.services;

import hello.entities.Message;

import java.util.List;

public interface ChatService {
    List<Message> getChannelMessages(int channelID);

}
