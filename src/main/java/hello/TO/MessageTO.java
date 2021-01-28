package hello.TO;

import hello.utilities.enums.MessageType;

public class MessageTO {
    private String from;
    private String text;
    private MessageType messageType;

    public MessageTO(String from, String text, MessageType messageType) {
        this.from = from;
        this.text = text;
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
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
        return "MessageTO{" +
                "from='" + from + '\'' +
                ", text='" + text + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
