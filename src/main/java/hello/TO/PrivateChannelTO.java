package hello.TO;

import java.io.Serializable;

public class PrivateChannelTO implements Serializable {
    private Long channelID;
    private String token;
    private boolean exists;

    public PrivateChannelTO(Long channelID, String token, boolean exists) {
        this.channelID = channelID;
        this.token = token;
        this.exists = exists;
    }

    public PrivateChannelTO() {
    }

    public Long getChannelID() {
        return channelID;
    }

    public void setChannelID(Long channelID) {
        this.channelID = channelID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
