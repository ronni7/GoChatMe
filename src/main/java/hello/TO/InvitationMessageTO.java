package hello.TO;

public class InvitationMessageTO {
    private String from;
    private String token;
    private String receiver;

    public InvitationMessageTO(String from, String token, String receiver) {
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
}
