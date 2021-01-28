package hello.TO;

import hello.utilities.enums.NotificationType;

import java.io.Serializable;

public class InvitationAcceptedOutputMessageTO implements Serializable {
    private String from;
    private String body;
    private Long receiverID;
    private NotificationType type;

    public InvitationAcceptedOutputMessageTO() {
    }

    public InvitationAcceptedOutputMessageTO(String from, String body, Long receiverID, NotificationType type) {
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

    public Long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Long receiverID) {
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
        return "InvitationAcceptedOutputMessageTO{" +
                "from='" + from + '\'' +
                ", body='" + body + '\'' +
                ", receiverID=" + receiverID +
                ", notificationType=" + type +
                '}';
    }
}
