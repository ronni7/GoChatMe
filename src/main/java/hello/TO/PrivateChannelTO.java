package hello.TO;

import hello.DTO.MessageOutputDTO;
import hello.entities.PrivateMessageOutput;

import java.io.Serializable;
import java.util.List;

public class PrivateChannelTO implements Serializable {
    private Long channelID;
    private String token;
    private boolean accepted;
    private List<MessageOutputDTO> messageList;

    public PrivateChannelTO(Long channelID, String token, boolean accepted, List<MessageOutputDTO> messageList) {
        this.channelID = channelID;
        this.token = token;
        this.accepted = accepted;
        this.messageList = messageList;
    }

    public PrivateChannelTO() {
    }

    public Long getChannelID() {
        return channelID;
    }

    public void setChannelID(Long channelID) {
        this.channelID = channelID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public List<MessageOutputDTO> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageOutputDTO> messageList) {
        this.messageList = messageList;
    }
}
