package hello.services;

import hello.entities.Message;
import hello.entities.MessageOutput;
import java.util.List;

public interface ChatService {
    List<Message> getChannelMessages(int channelID);
    Message sayHello();
   MessageOutput readAndSend(Message message) ;
}
