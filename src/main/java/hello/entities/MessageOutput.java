package hello.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class MessageOutput implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String sender;
    private String text;
    private String time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channelID")
    @JsonBackReference
    @NotFound(action = NotFoundAction.IGNORE)
    private Channel channel;

    public MessageOutput() {
    }

    public MessageOutput(String sender, String text, String time, Channel channel) {
        this.sender = sender;
        this.text = text;
        this.time = time;
        this.channel = channel;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "MessageOutput{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", channel=" + channel +
                '}';
    }
}
