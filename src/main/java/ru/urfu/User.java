package ru.urfu;

public class User {
    private static final int MAX_USER_NAME_LENGTH = 64;
    private final long userId;
    private String username;
    private PostStorage postStorage;

    User(String name) {
        setUsername(name);
        this.userId = Users.getCountId();
        postStorage = new PostStorage(this);
        Users.add(this);
    }

    public void setUsername(String name) throws IllegalArgumentException {
        if(name == null || name.isEmpty() || name.length() > MAX_USER_NAME_LENGTH)
            throw new IllegalArgumentException("Invalid username.");
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public PostStorage getPostStorage() {
        return postStorage;
    }

    public long getUserId() {
        return userId;
    }
}
