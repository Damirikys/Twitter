package ru.urfu;


public class Post {
    private static final int POST_MAX_LENGHT_TEXT = 150;
    private final String postId;
    private String text;
    private int index;

    Post(String text, String postId) {
        setText(text);
        this.postId = postId;
    }

    private void setPost(Post post){
        setText(post.getText());
    }

    public void setText(String text) throws IllegalArgumentException {
        if(text == null || text.isEmpty() || text.length() > POST_MAX_LENGHT_TEXT)
            throw new IllegalArgumentException("Invalid post text.");
        this.text = text.replace("<", "&lt;");
    }

    public String getText(){
        return text;
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int id) {
        this.index = id;
    }

    public String getPostId() {
        return postId;
    }
}
