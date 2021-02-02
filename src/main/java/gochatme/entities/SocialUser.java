package gochatme.entities;

import java.io.Serializable;

public class SocialUser implements Serializable {
    private String firstName;
    private String email;
    private String lastName;
    private String name;

    public SocialUser() {
    }

    public SocialUser(String firstName, String email, String lastName, String name) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User toUser() {
        User user = new User();
        user.setEmail(this.email);
        user.setLogin(this.email);
        user.setName(this.firstName);
        user.setSurname(this.lastName);
        user.setNickname(this.name);
        return user;
    }

    @Override
    public String toString() {
        return "SocialUser{" +
                "firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
