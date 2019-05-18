package hello.entities;

import java.util.List;

public class Channel {
    private int channelID;
    private String nameOfChannel;
    private List<Message> messageList;

    public Channel(int channelID, String nameOfChannel, List<Message> messageList) {
        this.channelID = channelID;
        this.nameOfChannel = nameOfChannel;
        this.messageList = messageList;
    }

    public int getChannelID() {
        return channelID;
    }

    public String getNameOfChannel() {
        return nameOfChannel;
    }

    public List<Message> getMessageList() {
        return messageList;
    }
}
