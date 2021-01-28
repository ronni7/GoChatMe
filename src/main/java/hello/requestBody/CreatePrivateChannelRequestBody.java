package hello.requestBody;

import java.io.Serializable;

public class CreatePrivateChannelRequestBody implements Serializable {
    private int senderID;
    private String destinationUserNickname;

    public CreatePrivateChannelRequestBody(int senderID, String destinationUserNickname) {
        this.senderID = senderID;
        this.destinationUserNickname = destinationUserNickname;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public String getDestinationUserNickname() {
        return destinationUserNickname;
    }

    public void setDestinationUserNickname(String destinationUserNickname) {
        this.destinationUserNickname = destinationUserNickname;
    }

    @Override
    public String toString() {
        return "CreatePrivateChannelRequestBody{" +
                "senderID=" + senderID +
                ", destinationUserNickname='" + destinationUserNickname + '\'' +
                '}';
    }
}
