package hello.services;

import hello.entities.Channel;
import hello.entities.Message;
import hello.entities.MessageOutput;
import hello.utilities.enums.MESSAGETYPE;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
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
        list.add(new Message("1","lol wiadomosc", MESSAGETYPE.NORMAL));
        list.add(new Message("1","lol odpowiedz",MESSAGETYPE.NORMAL));
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
    public MessageOutput readAndSend(Message message) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new MessageOutput(message.getFrom(), message.getText(), time);
    }

    @Override
    public MessageOutput sendGreeting(SimpMessageHeaderAccessor headerAccessor) {
        String username=headerAccessor.getNativeHeader("username").get(0);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new MessageOutput("Server", username +" has joined channel", time);
    }
}
