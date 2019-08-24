package hello.services;

import hello.entities.Channel;
import hello.entities.Message;
import hello.entities.MessageOutput;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ChatServiceImpl implements ChatService {
    private List<Channel> channelList;

    public ChatServiceImpl() {
        this.channelList=new ArrayList<>();
    List<Message> list=   new ArrayList<>();
    list.add(new Message("1","lol wiadomosc"));
    list.add(new Message("1","lol odpowiedz"));
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

    @Override
    public Message sayHello() {
        return new Message("server","Hello in my chatroom, friend");
    }

    @Override
    public MessageOutput readAndSend(Message message) {
            String time = new SimpleDateFormat("HH:mm").format(new Date());
            return new MessageOutput(message.getFrom(), message.getText(), time);
    }
}
