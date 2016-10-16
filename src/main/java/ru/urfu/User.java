package ru.urfu;

import java.util.HashMap;

public class User {
    private String username;
    private PostStorage postStorage;

    User(String name) {
        setUsername(name);
        postStorage = new PostStorage();
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public PostStorage getPostStorage() {
        return postStorage;
    }
}
