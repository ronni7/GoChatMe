package hello.services;

import hello.entities.Channel;
import hello.entities.Message;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class ChatServiceImpl implements ChatService {
    private List<Channel> channelList;

    public ChatServiceImpl() {
        this.channelList=new ArrayList<>();
    List<Message> list=   new ArrayList<>();
    list.add(new Message(2,"lol wiadomosc"));
    list.add(new Message(1,"lol odpowiedz"));
        channelList.add(
                new Channel(
                        1,
                        "Podstawowka",
                        list)
                );


    }

    public ChatServiceImpl(List<Channel> channelList) {
        this.channelList = channelList;
    }

    @Override
    public List<Message> getChannelMessages(int channelID) {
       return channelList.stream().filter(channel -> channel.getChannelID()==channelID).findFirst().get().getMessageList();
    }
}
