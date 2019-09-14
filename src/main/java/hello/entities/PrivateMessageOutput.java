package hello.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PrivateMessageOutput implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sender;
    private String text;
    private String time;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "channelID")
    @JsonBackReference
    @NotFound(action = NotFoundAction.IGNORE)
    private PrivateChannel privateChannel = new PrivateChannel(); //todo problem with toString()

    public PrivateMessageOutput(String sender, String text, String time, PrivateChannel privateChannel) {
        this.sender = sender;
        this.text = text;
        this.time = time;
        this.privateChannel = privateChannel;
    }

    public PrivateMessageOutput() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public PrivateChannel getPrivateChannel() {
        return privateChannel;
    }

    public void setPrivateChannel(PrivateChannel privateChannel) {
        this.privateChannel = privateChannel;
    }

}
