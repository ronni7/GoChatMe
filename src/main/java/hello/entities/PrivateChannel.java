package hello.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PrivateChannel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long channelID;
    private String token;
    @OneToMany(mappedBy = "privateChannel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonManagedReference
    @OrderBy
    private List<PrivateMessageOutput> messageList = new ArrayList<>();

    @Override
    public String toString() {
        return "PrivateChannel{" +
                "channelID=" + channelID +
                ", token='" + token + '\'' +
                ", messageList=" + messageList +
                '}';
    }

    public PrivateChannel() {
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

    public List<PrivateMessageOutput> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<PrivateMessageOutput> messageList) {
        this.messageList = messageList;
    }

}

