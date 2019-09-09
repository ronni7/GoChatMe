package hello.entities;

import java.io.Serializable;

public class InvitationMessageOutput implements Serializable {
    private String from;
    private String token;
    private String receiverID;

    public InvitationMessageOutput() {
    }

    public InvitationMessageOutput(String from, String token, String receiverID) {
        this.from = from;
        this.token = token;
        this.receiverID = receiverID;
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

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }
}
