package gochatme.TO;

import gochatme.utilities.enums.NotificationType;

public class InvitationMessageTO {
    private NotificationType type;
    private String from;
    private String token;
    private String receiver;

    public InvitationMessageTO(NotificationType type, String from, String token, String receiver) {
        this.type = type;
        this.from = from;
        this.token = token;
        this.receiver = receiver;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "InvitationMessageTO{" +
                "type=" + type +
                ", from='" + from + '\'' +
                ", token='" + token + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
