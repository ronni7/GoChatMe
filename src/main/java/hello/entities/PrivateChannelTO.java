package hello.entities;

import java.io.Serializable;

public class PrivateChannelTO implements Serializable {
    private String token;
    private boolean exists;

    public PrivateChannelTO() {
    }

    public PrivateChannelTO(String token, boolean exists) {
        this.token = token;
        this.exists = exists;
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
