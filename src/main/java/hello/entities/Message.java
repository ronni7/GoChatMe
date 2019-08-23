package hello.entities;

public class Message {
    private int senderID;
    private String messageContent;

    public Message(int senderID, String messageContent) {
        this.senderID = senderID;
        this.messageContent = messageContent;
    }

    public int getSenderID() {
        return senderID;
    }

    public String getMessageContent() {
        return messageContent;
    }
}
