package gochatme.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Arrays;

@Entity
public class User implements Serializable {
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String nickname;
    private char[] password;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String name, String surname, String login, String nickname, char[] password, String email) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password=" + Arrays.toString(password) +
                ", email='" + email + '\'' +
                '}';
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public boolean equalsSocialUser(SocialUser socialUser) {
        if (!this.getLogin().equals(socialUser.getEmail()))
            return false;
        return this.getEmail().equals(socialUser.getEmail());
    }

    public void synchronize(SocialUser socialUser) {
        User newUser = socialUser.toUser();
        this.setName(newUser.getName());
        this.setSurname(newUser.getSurname());
        this.setNickname(newUser.getNickname());
    }
}

