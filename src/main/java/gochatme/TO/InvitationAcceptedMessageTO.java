package gochatme.TO;

import gochatme.utilities.enums.NotificationType;

public class InvitationAcceptedMessageTO {
    private Long senderID;
    private String receiver;
    private NotificationType type;
    private String token;

    public InvitationAcceptedMessageTO(Long senderID, String receiverID, NotificationType type, String token) {
        this.senderID = senderID;
        this.receiver = receiverID;
        this.type = type;
        this.token = token;
    }

    public Long getSenderID() {
        return senderID;
    }

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "InvitationAcceptedMessageTO{" +
                "senderID=" + senderID +
                ", receiver='" + receiver + '\'' +
                ", type=" + type +
                ", token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
