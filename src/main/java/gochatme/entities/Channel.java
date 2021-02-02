package gochatme.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Channel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelID;
    private String name;
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonManagedReference
    @OrderBy
    private List<MessageOutput> messageList;
    private String description;
    private boolean active;
    private boolean adultsOnly;

    public Channel(String name, List<MessageOutput> messageList, String description, boolean active, boolean adultsOnly) {
        this.name = name;
        this.messageList = messageList;
        this.description = description;
        this.active = active;
        this.adultsOnly = adultsOnly;
    }

    public Channel() {
    }

    public Long getChannelID() {
        return channelID;
    }

    public void setChannelID(Long channelID) {
        this.channelID = channelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MessageOutput> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageOutput> messageList) {
        this.messageList = messageList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAdultsOnly() {
        return adultsOnly;
    }

    public void setAdultsOnly(boolean adultsOnly) {
        this.adultsOnly = adultsOnly;
    }

}

