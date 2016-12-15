package ru.urfu.entities;

import javax.persistence.*;

@Entity
@Table(name = "TBL_USER")
public class User
{
    private static final int MAX_USER_NAME_LENGTH = 64;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="login", nullable = false)
    private String login;
    @Column(name="password", nullable = false)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return login;
    }

    public void setUsername(String name) throws IllegalArgumentException {
        if(name == null || name.isEmpty() || name.length() > MAX_USER_NAME_LENGTH)
            throw new IllegalArgumentException("Invalid username.");
        this.login = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}