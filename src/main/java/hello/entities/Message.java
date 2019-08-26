package hello.entities;

import hello.utilities.enums.MESSAGETYPE;

public class Message {
    private String from;
    private String text;
    private MESSAGETYPE messageType;

    public Message(String from, String text, MESSAGETYPE messageType) {
        this.from = from;
        this.text = text;
        this.messageType = messageType;
    }

    public MESSAGETYPE getMessageType() {
        return messageType;
    }

    public void setMessageType(MESSAGETYPE messageType) {
        this.messageType = messageType;
    }

    public Message() {

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", text='" + text + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
