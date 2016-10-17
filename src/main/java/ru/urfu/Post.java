package ru.urfu;


public class Post {
    private static final int POST_MAX_LENGHT_TEXT = 150;
    private User author;
    private final long postId;
    private String text;

    Post(User user, String text, long postId) {
        setText(text);
        this.author = user;
        this.postId = postId;
    }

    public void setText(String text) throws IllegalArgumentException {
        if(text == null || text.isEmpty() || text.length() > POST_MAX_LENGHT_TEXT)
            throw new IllegalArgumentException("Invalid post text.");
        this.text = text.replace("<", "&lt;");
    }

    public String getText(){
        return text;
    }

    public long getId() {
        return postId;
    }

    public User getAuthor() {
        return author;
    }
}
