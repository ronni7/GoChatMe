package gochatme.TO;

import gochatme.utilities.enums.NotificationType;

import java.io.Serializable;

public class InvitationMessageOutputTO implements Serializable {
    private String from;
    private String body;
    private String receiverID;
    private NotificationType type;

    public InvitationMessageOutputTO() {
    }

    public InvitationMessageOutputTO(String from, String body, String receiverID, NotificationType type) {
        this.from = from;
        this.body = body;
        this.receiverID = receiverID;
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "InvitationMessageOutputTO{" +
                "from='" + from + '\'' +
                ", token='" + body + '\'' +
                ", receiverID='" + receiverID + '\'' +
                ", type=" + type +
                '}';
    }
}
